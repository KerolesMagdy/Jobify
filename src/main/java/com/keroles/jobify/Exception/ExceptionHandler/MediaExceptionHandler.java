package com.keroles.jobify.Exception.ExceptionHandler;

import com.keroles.jobify.Exception.ExceptionUtil;
import com.keroles.jobify.Exception.Exceptions.Mail.MailAuthException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailMessageCreationException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailPrepException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailSendingException;
import com.keroles.jobify.Exception.Exceptions.Media.FileNotSupportedException;
import com.keroles.jobify.Exception.Exceptions.Media.FileUploadFailedException;
import com.keroles.jobify.Exception.Exceptions.Media.MediaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class MediaExceptionHandler {

    private final ExceptionUtil exceptionUtil;

    public MediaExceptionHandler(ExceptionUtil exceptionUtil) {
        this.exceptionUtil = exceptionUtil;
    }

    @ExceptionHandler(FileNotSupportedException.class)
    public final ResponseEntity<Object> handleFileNotSupportedException(FileNotSupportedException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.media.file.not_supported", request);
    }
    @ExceptionHandler(FileUploadFailedException.class)
    public final ResponseEntity<Object> handleFileUploadFailedException(FileUploadFailedException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.media.file.upload.failed", request);
    }
    @ExceptionHandler(MediaNotFoundException.class)
    public final ResponseEntity<Object> handleMediaNotFoundException(MediaNotFoundException ex, WebRequest request) {
        return exceptionUtil.prepareCustomExceptionResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "validate.message.media.file.not_found", request);
    }
}
