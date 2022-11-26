package com.keroles.jobify.Exception.ExceptionHandler;

import com.keroles.jobify.Exception.ExceptionUtil;
import com.keroles.jobify.Exception.Exceptions.Mail.MailAuthException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailMessageCreationException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailPrepException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailSendingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class MailExceptionHandler {

    private final ExceptionUtil exceptionUtil;

    public MailExceptionHandler(ExceptionUtil exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }

    @ExceptionHandler(MailMessageCreationException.class)
    public final ResponseEntity<Object> handleMailMessageCreationException(MailMessageCreationException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.mail.parse-exception", request);
    }
    @ExceptionHandler(MailAuthException.class)
    public final ResponseEntity<Object> handleMailAuthException(MailAuthException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.mail.authentication-exception", request);
    }
    @ExceptionHandler(MailSendingException.class)
    public final ResponseEntity<Object> handleMailSendingException(MailSendingException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.mail.send-exception", request);
    }
    @ExceptionHandler(MailPrepException.class)
    public final ResponseEntity<Object> handleMailPrepException(MailPrepException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.mail.preparation-exception", request);
    }

}
