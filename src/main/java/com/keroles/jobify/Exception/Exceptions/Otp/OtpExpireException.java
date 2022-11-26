package com.keroles.jobify.Exception.Exceptions.Otp;

public class OtpExpireException extends RuntimeException{
    public OtpExpireException() {
    }

    public OtpExpireException(String message) {
        super(message);
    }
}
