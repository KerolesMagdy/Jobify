/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keroles.jobify.Sec.Exception.Handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keroles.jobify.Sec.Exception.Exceptions.TokenAuthInvalidException;
import com.keroles.jobify.Sec.Exception.Exceptions.UserAuthFailedException;
import com.keroles.jobify.Sec.Exception.Exceptions.UserVerificationRequiredException;
import com.keroles.jobify.Sec.Exception.ResponseModel.ExceptionAuthResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class AuthFailureHandler {

    private final Environment environment;

    public AuthFailureHandler(Environment environment) {
        this.environment = environment;
    }

    public final void handleTokenAuthInvalidException(Exception exception,HttpServletResponse response,HttpServletRequest request,String error_message) throws IOException {
        ExceptionAuthResponseModel exceptionAuthResponseModel = ExceptionAuthResponseModel.builder()
                    .timestamp(new Date())
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .error(HttpStatus.UNAUTHORIZED)
                    .message(getExceptionMessage(error_message, environment.getProperty("validate.message.user.auth.token.failed")))
                    .path(request.getServletPath())
                    .build();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), exceptionAuthResponseModel);

    }

    public final void handleTokenAuthExpiredException(TokenAuthInvalidException exception,HttpServletResponse response) throws IOException {
      ExceptionAuthResponseModel exceptionAuthResponseModel=exception.getExceptionAuthResponseModel();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("error", exception != null ? exception.getMessage() : environment.getProperty("validate.message.user.auth.token.not_found"));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), exceptionAuthResponseModel);
    }
    public final void handleAccountNotVerifiedException(UserVerificationRequiredException exception, HttpServletResponse response) throws IOException {
        ExceptionAuthResponseModel exceptionAuthResponseModel=exception.getExceptionAuthResponseModel();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("error", exception != null ? exception.getMessage() : environment.getProperty("validate.message.user.auth.token.not_verified"));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), exceptionAuthResponseModel);
    }

    public final ResponseEntity<Object> handleUserAuthFailedException(HttpServletResponse response,HttpServletRequest request,String error_message) throws IOException {
        ExceptionAuthResponseModel exceptionAuthResponseModel =
                ExceptionAuthResponseModel.builder()
                        .timestamp(new Date())
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .error(HttpStatus.UNAUTHORIZED)
                        .message(getExceptionMessage(error_message,environment.getProperty("validate.message.user.auth.failed")))
                        .path(request.getServletPath())
                        .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        new ObjectMapper().writeValue(response.getOutputStream(), exceptionAuthResponseModel);
        return new ResponseEntity(exceptionAuthResponseModel, exceptionAuthResponseModel.getError());
    }

    private String getExceptionMessage(String error_message, String exception_message){
        return error_message==null?exception_message:error_message;

    }

    
    
}
