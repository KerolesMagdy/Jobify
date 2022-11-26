package com.keroles.jobify.Exception.Exceptions.User;

public class UserDuplicateException extends RuntimeException{
    public UserDuplicateException() {
    }

    public UserDuplicateException(String message) {
        super(message);
    }
}
