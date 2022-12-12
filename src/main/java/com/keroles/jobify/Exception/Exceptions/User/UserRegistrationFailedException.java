package com.keroles.jobify.Exception.Exceptions.User;

public class UserRegistrationFailedException extends RuntimeException{
    public UserRegistrationFailedException() {
    }

    public UserRegistrationFailedException(String message) {
        super(message);
    }
}
