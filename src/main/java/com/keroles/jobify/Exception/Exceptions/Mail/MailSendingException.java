package com.keroles.jobify.Exception.Exceptions.Mail;

public class MailSendingException extends RuntimeException{
    public MailSendingException() {
    }

    public MailSendingException(String message) {
        super(message);
    }
}
