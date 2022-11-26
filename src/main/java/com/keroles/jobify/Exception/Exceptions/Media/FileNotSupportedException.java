package com.keroles.jobify.Exception.Exceptions.Media;

public class FileNotSupportedException extends RuntimeException{
    public FileNotSupportedException() {
    }

    public FileNotSupportedException(String message) {
        super(message);
    }
}
