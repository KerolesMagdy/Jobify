package com.keroles.jobify.Exception.Exceptions.Media;

public class FileUploadFailedException extends RuntimeException{
    public FileUploadFailedException() {
    }

    public FileUploadFailedException(String message) {
        super(message);
    }
}
