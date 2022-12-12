package com.keroles.jobify.Exception.Exceptions.Media;

public class MediaNotFoundException extends RuntimeException{
    public MediaNotFoundException() {
    }

    public MediaNotFoundException(String message) {
        super(message);
    }
}
