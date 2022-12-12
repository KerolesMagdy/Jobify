package com.keroles.jobify.Sec.AuthFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Sec.Exception.Exceptions.TokenAuthInvalidException;
import com.keroles.jobify.Sec.Exception.Exceptions.UserVerificationRequiredException;
import com.keroles.jobify.Sec.Exception.Handler.AuthFailureHandler;
import com.keroles.jobify.Sec.Exception.ResponseModel.ExceptionAuthResponseModel;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import com.keroles.jobify.Sec.Token.Util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class AuthFilterUtil {
    public enum UserType{USER,EMPLOYER}
    @Autowired
    AuthFailureHandler authFailureHandler;
    @Autowired
    private Environment environment;
    @Autowired
    private TokenUtils tokenUtils;
    protected void onSuccessfulAuth(HttpServletResponse response, Object responseBody) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }

    protected void onFailureAuth(HttpServletResponse response, HttpServletRequest request, String error_message) throws IOException {
        authFailureHandler.handleUserAuthFailedException(response,request,error_message);
    }

    protected void onFailureAuthInvalidToken(Exception exception,HttpServletResponse response,HttpServletRequest request,String error_message) throws IOException {
        authFailureHandler.handleTokenAuthInvalidException(exception,response,request,error_message);
    }

    protected void onFailureAuthExpiredToken(HttpServletResponse response,HttpServletRequest request) throws IOException {
        ExceptionAuthResponseModel exceptionAuthResponseModel = ExceptionAuthResponseModel
                .builder()
                .timestamp(new Date())
                .error(HttpStatus.FORBIDDEN)
                .status(HttpStatus.FORBIDDEN.value())
                .message(environment.getProperty("validate.message.user.auth.token.expired"))
                .path(request.getServletPath())
                .build();

        authFailureHandler.handleTokenAuthExpiredException(new TokenAuthInvalidException(
                environment.getProperty("validate.message.user.auth.token.expired"),
                exceptionAuthResponseModel),response);
    }

    protected void onAccountNotVerified( HttpServletResponse response,HttpServletRequest request) throws IOException {
        ExceptionAuthResponseModel exceptionAuthResponseModel = ExceptionAuthResponseModel
                .builder()
                .timestamp(new Date())
                .error(HttpStatus.FORBIDDEN)
                .status(HttpStatus.FORBIDDEN.value())
                .message(environment.getProperty("validate.message.user.auth.token.not_verified"))
                .path(request.getServletPath())
                .build();

        authFailureHandler.handleAccountNotVerifiedException(new UserVerificationRequiredException(
                environment.getProperty("validate.message.user.auth.token.not_verified"),
                exceptionAuthResponseModel),response);
    }

    protected CompositeToken prepareTokenToAuthResponse(Collection<GrantedAuthority> authorities,char[] username,UserType type){
        List<char[]> roles=new ArrayList<>();

        authorities.forEach(grantedAuthority -> roles.add(grantedAuthority.getAuthority().toCharArray()));
        return tokenUtils.generateCompositeToken(username, roles,type);
    }
}
