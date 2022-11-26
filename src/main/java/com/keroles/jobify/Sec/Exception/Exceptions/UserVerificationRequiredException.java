package com.keroles.jobify.Sec.Exception.Exceptions;

import com.keroles.jobify.Sec.Exception.ResponseModel.ExceptionAuthResponseModel;
import lombok.Getter;

public class UserVerificationRequiredException extends RuntimeException{
    @Getter
    ExceptionAuthResponseModel exceptionAuthResponseModel;

    public UserVerificationRequiredException() {
    }

    public UserVerificationRequiredException(String message
            ,ExceptionAuthResponseModel exceptionAuthResponseModel) {
        super(message);
        this.exceptionAuthResponseModel=exceptionAuthResponseModel;
    }
}
