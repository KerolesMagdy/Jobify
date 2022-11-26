package com.keroles.jobify.Exception.ExceptionHandler;

import com.keroles.jobify.Exception.ExceptionUtil;
import com.keroles.jobify.Exception.Exceptions.CareerInterests.UserCareerInterestsIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice

public class UserCareerInterestsExceptionHandler {
    private final ExceptionUtil exceptionUtil;

    public UserCareerInterestsExceptionHandler(ExceptionUtil exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }
    @ExceptionHandler(UserCareerInterestsIdException.class)
    public final ResponseEntity<Object> handleUserCareerInterestsIdException(UserCareerInterestsIdException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.BAD_REQUEST, "validate.message.user.career_interests.false_id", request);
    }


}
