package com.keroles.jobify.Exception;

import com.keroles.jobify.Exception.ExceptionResponseModel.ExceptionResponseModel;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ExceptionUtil {

    private final Environment environment;

    public ExceptionUtil(Environment environment) {
        this.environment = environment;
    }

    public  ResponseEntity<Object> prepareCustomExceptionResponse(RuntimeException ex, HttpStatus error, String Property, WebRequest request){
        List<String> error_message=new ArrayList<>();
        error_message.add(getExceptionMessage(ex,environment.getProperty(Property)));
        ExceptionResponseModel userExceptionResponseModel =
                ExceptionResponseModel.builder()
                        .timestamp(new Date())
                        .status(error.value())
                        .error(error)
                        .message(error_message)
                        .path(request.getDescription(false))
                        .build();

        return new ResponseEntity(userExceptionResponseModel, userExceptionResponseModel.getError());
    }
    private String getExceptionMessage(RuntimeException exception, String exception_message){
        return exception.getMessage()==null?exception_message:exception.getMessage();

    }
}
