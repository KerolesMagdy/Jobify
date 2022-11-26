package com.keroles.jobify.Sec.Exception.Exceptions;

import com.keroles.jobify.Sec.Exception.ResponseModel.ExceptionAuthResponseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class TokenAuthInvalidException extends RuntimeException{
    @Getter
    ExceptionAuthResponseModel exceptionAuthResponseModel;
    public TokenAuthInvalidException() {
    }

    public TokenAuthInvalidException(String message,ExceptionAuthResponseModel exceptionAuthResponseModel) {
        super(message);
        this.exceptionAuthResponseModel=exceptionAuthResponseModel;
    }


}
