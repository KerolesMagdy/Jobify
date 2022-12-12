package com.keroles.jobify.Mail;

import com.keroles.jobify.Exception.Exceptions.Mail.MailAuthException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailMessageCreationException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailPrepException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailSendingException;
import com.keroles.jobify.Mail.Model.AttachedMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}") private String sender;

    public boolean sendSimpleMail(char[] mailTo,char[]subject,char[]body) {
        try {
            emailSender.send(prepareSimpleMail(mailTo,subject,body));
        }catch (MailAuthenticationException exception){
            throw new MailAuthException();
        }catch (MailPreparationException ex){
            throw new MailPrepException();
        }catch (MailSendException ex){
            throw new MailSendingException();
        }catch (MailParseException ex){
            throw new MailMessageCreationException();
        }
        return true;

    }

    public boolean sendMailWithAttachment(AttachedMail details) {
        return true;
    }

    private SimpleMailMessage prepareSimpleMail(char[] mailTo,char[]subject,char[]body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(String.valueOf(mailTo));
        message.setSubject(String.valueOf(subject));
        message.setText(String.valueOf(body));
        return message;
    }
}
