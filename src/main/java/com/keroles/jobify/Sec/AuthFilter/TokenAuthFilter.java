package com.keroles.jobify.Sec.AuthFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import com.keroles.jobify.Sec.Authentication.UserPassAuthToken;
import com.keroles.jobify.Sec.Exception.Exceptions.TokenAuthInvalidException;
import com.keroles.jobify.Sec.Exception.Exceptions.UserVerificationRequiredException;
import com.keroles.jobify.Sec.Exception.Handler.AuthFailureHandler;
import com.keroles.jobify.Sec.Exception.ResponseModel.ExceptionAuthResponseModel;
import com.keroles.jobify.Sec.Token.Model.TokenModel;
import com.keroles.jobify.Sec.ApiPath.AuthPath;
import com.keroles.jobify.Sec.Token.Util.TokenUtils;
import com.keroles.jobify.Service.Implementation.UserService;
import com.keroles.jobify.Model.UsersDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private AuthenticationManager authManager;
    @Autowired
    private AuthPath authPath;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private AuthFailureHandler authFailureHandler;
    @Autowired
    private Environment environment;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization=request.getHeader("Authorization");
        if(authorization!=null&&authorization.startsWith("Bearer ")){
            String token = authorization.substring("Bearer ".length());
            try {
                TokenModel tokenModel = tokenUtils.getTokenModel(token);
                UsersDetails userDetails = userService.loadUserByUsername(tokenModel.getUsername());
                if (userDetails==null)

                onFailureAuthInvalidToken(
                        new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"))
                        ,response
                        ,request
                        ,null);
                if (userDetails!=null && !userDetails.isEnabled()) {
                    onAccountNotVerified(response, request);
                    return;
                }
                if (tokenModel!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                    if (!tokenUtils.validateToken(tokenModel,userDetails)) {

                        onFailureAuthExpiredToken(response, request);
                        return;
                    } else {
                        UserPassAuthToken userPassAuthToken = new UserPassAuthToken(tokenModel.getUsername(),
                                null,
                                tokenModel.getAuthority());
                        userPassAuthToken.getAuthorities().forEach(grantedAuthority -> log.error("222  "+grantedAuthority.getAuthority()));
                        SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
                        if(request.getServletPath().equals("/token/refresh")){
                            CompositeToken compositeToken= tokenUtils.generateCompositeToken(
                                    userPassAuthToken.getName(),
                                    userPassAuthToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                                    ,tokenUtils.getACCESS_TOKEN_VALIDITY());
                            onSuccessfulAuth(response, compositeToken);

                        }
                        filterChain.doFilter(request, response);
                    }
                }
            }catch (Exception ex){
                log.error("11111111111111"+ex.getMessage());
                onFailureAuthInvalidToken(ex,response,request,null);}
        }else {onFailureAuthInvalidToken(null,response,request,null);}
    }



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        for (String s : authPath.getTokenShouldNotDo())
            if (request.getServletPath().startsWith(s)) return true;
        return false;
    }
    private void onFailureAuthInvalidToken(Exception exception,HttpServletResponse response,HttpServletRequest request,String error_message) throws IOException {
        authFailureHandler.handleTokenAuthInvalidException(exception,response,request,error_message);
    }

    private void onFailureAuthExpiredToken(HttpServletResponse response,HttpServletRequest request) throws IOException {
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

    private void onAccountNotVerified( HttpServletResponse response,HttpServletRequest request) throws IOException {
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


    private void onSuccessfulAuth(HttpServletResponse response,Object responseBody) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getOutputStream(), responseBody);
    }
}
