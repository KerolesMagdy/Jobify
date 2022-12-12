package com.keroles.jobify.Exception.Exceptions.Media;

public class MediaServerErrorException extends RuntimeException{
    public MediaServerErrorException() {
    }

    public MediaServerErrorException(String message) {
        super(message);
    }
}
