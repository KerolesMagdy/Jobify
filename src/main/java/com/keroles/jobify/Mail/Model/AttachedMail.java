package com.keroles.jobify.Mail.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachedMail {
    private String attachment;
    private String recipient;
    private String subject;
    private String msgBody;
}
