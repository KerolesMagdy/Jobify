package com.keroles.jobify.Exception.Exceptions.Mail;

public class MailMessageCreationException extends RuntimeException{
    public MailMessageCreationException() {
    }

    public MailMessageCreationException(String message) {
        super(message);
    }
}
