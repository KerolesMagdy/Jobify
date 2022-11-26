package com.keroles.jobify.Sec.Exception.Exceptions;

public class UserAuthFailedException extends RuntimeException{

    public UserAuthFailedException() {
    }

    public UserAuthFailedException(String message) {
        super(message);
    }
}
