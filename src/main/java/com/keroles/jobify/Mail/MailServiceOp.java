package com.keroles.jobify.Mail;

import com.keroles.jobify.Mail.Model.AttachedMail;
import com.keroles.jobify.Mail.Model.SimpleMail;

public interface MailServiceOp {
    boolean sendSimpleMail(SimpleMail details);
    boolean sendMailWithAttachment(AttachedMail details);
}
