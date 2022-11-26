package com.keroles.jobify.Mail.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMail {
    private String recipient;
    private String subject;
    private String msgBody;
    
    
    
}
