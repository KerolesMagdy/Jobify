package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDto {
    private Long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM")
    private Date dateAwarded;
    private String organizationName;
    private int score;
    private String certificateLink;
    private String additionalInfo;
}
