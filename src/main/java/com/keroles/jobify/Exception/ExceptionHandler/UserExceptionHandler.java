/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keroles.jobify.Exception.ExceptionHandler;

import com.keroles.jobify.Exception.ExceptionResponseModel.ExceptionArgsResponseModel;
import com.keroles.jobify.Exception.ExceptionUtil;
import com.keroles.jobify.Exception.Exceptions.User.UserOpNotAuthException;
import com.keroles.jobify.Exception.Exceptions.User.UserDuplicateException;
import com.keroles.jobify.Exception.Exceptions.User.UserRoleDuplicateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Keroles Magdy
 */
@RestControllerAdvice
public class UserExceptionHandler {

    private final ExceptionUtil exceptionUtil;

    public UserExceptionHandler(ExceptionUtil exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }

    @ExceptionHandler(UserDuplicateException.class)
    public final ResponseEntity<Object> handleUserDuplicateException(UserDuplicateException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.user.duplicate", request);
    }
    @ExceptionHandler(UserRoleDuplicateException.class)
    public final ResponseEntity<Object> handleUserRoleDuplicateException(UserRoleDuplicateException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.user.role.duplicate", request);
    }

    @ExceptionHandler(UserOpNotAuthException.class)
    public final ResponseEntity<Object> handleUserOpNotAuthException(UserOpNotAuthException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex,HttpStatus.FORBIDDEN, "validate.message.user.auth.op.no_auth", request);
    }
    
}
