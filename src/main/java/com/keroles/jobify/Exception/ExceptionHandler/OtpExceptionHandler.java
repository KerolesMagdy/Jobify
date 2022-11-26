package com.keroles.jobify.Exception.ExceptionHandler;

import com.keroles.jobify.Exception.ExceptionUtil;
import com.keroles.jobify.Exception.Exceptions.Otp.OtpExpireException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class OtpExceptionHandler {
    private final ExceptionUtil exceptionUtil;

    public OtpExceptionHandler(ExceptionUtil exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }

    @ExceptionHandler(OtpExpireException.class)
    public final ResponseEntity<Object> handleOtpExpireException(OtpExpireException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.otp.expired", request);
    }
}
