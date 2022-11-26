package com.keroles.jobify.Exception.ExceptionHandler;

import com.keroles.jobify.Exception.ExceptionResponseModel.ExceptionArgsResponseModel;
import com.keroles.jobify.Exception.ExceptionUtil;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
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

@RestControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
    private final ExceptionUtil exceptionUtil;

    public GlobalHandler(ExceptionUtil exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }

    @ExceptionHandler(GlobalObjectNotFoundException.class)
    public final ResponseEntity<Object> handleGlobalObjectNotFoundException(GlobalObjectNotFoundException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.object.not_found", request);
    }

    @ExceptionHandler(GlobalObjectFoundException.class)
    public final ResponseEntity<Object> handleGlobalObjectFoundException(GlobalObjectFoundException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.object.found", request);
    }

    @ExceptionHandler(GlobalIdNotFoundException.class)
    public final ResponseEntity<Object> handleGlobalIdNotFoundException(GlobalIdNotFoundException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.object.id.not_found", request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors=new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> errors.add(fieldError.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(objectError -> errors.add(objectError.getDefaultMessage()));
        ExceptionArgsResponseModel userExceptionResponseModel=
                new ExceptionArgsResponseModel(
                        new Date()
                        ,HttpStatus.BAD_REQUEST.value()
                        , HttpStatus.BAD_REQUEST
                        ,errors
                        , request.getDescription(false));

        return new ResponseEntity(userExceptionResponseModel, userExceptionResponseModel.getError());
    }

}
