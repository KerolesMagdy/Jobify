package com.keroles.jobify.Mail;

import com.keroles.jobify.Exception.Exceptions.Mail.MailAuthException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailMessageCreationException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailPrepException;
import com.keroles.jobify.Exception.Exceptions.Mail.MailSendingException;
import com.keroles.jobify.Mail.Model.AttachedMail;
import com.keroles.jobify.Mail.Model.SimpleMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailServiceOp {
    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}") private String sender;

    @Override
    public boolean sendSimpleMail(SimpleMail details) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(details.getRecipient());
            message.setSubject(details.getSubject());
            message.setText(details.getMsgBody());
            emailSender.send(message);
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

    @Override
    public boolean sendMailWithAttachment(AttachedMail details) {
        return true;
    }
}
