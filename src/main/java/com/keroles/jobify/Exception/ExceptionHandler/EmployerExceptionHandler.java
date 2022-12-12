package com.keroles.jobify.Exception.ExceptionHandler;

import com.keroles.jobify.Exception.ExceptionUtil;
import com.keroles.jobify.Exception.Exceptions.Employer.EmployerDuplicateException;
import com.keroles.jobify.Exception.Exceptions.Employer.EmployerRegistrationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class EmployerExceptionHandler {

    private final ExceptionUtil exceptionUtil;

    public EmployerExceptionHandler(ExceptionUtil exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }

    @ExceptionHandler(EmployerDuplicateException.class)
    public final ResponseEntity<Object> handleEmployerDuplicateException(EmployerDuplicateException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.employer.duplicate", request);
    }

    @ExceptionHandler(EmployerRegistrationFailedException.class)
    public final ResponseEntity<Object> handleEmployerRegistrationFailedException(EmployerRegistrationFailedException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex,HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.employer.register.failed", request);
    }
}
