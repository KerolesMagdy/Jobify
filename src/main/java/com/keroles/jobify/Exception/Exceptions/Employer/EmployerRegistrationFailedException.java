package com.keroles.jobify.Exception.Exceptions.Employer;

public class EmployerRegistrationFailedException extends RuntimeException{
    public EmployerRegistrationFailedException() {
    }

    public EmployerRegistrationFailedException(String message) {
        super(message);
    }
}
