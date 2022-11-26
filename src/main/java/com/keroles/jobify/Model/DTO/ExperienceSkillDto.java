package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.keroles.jobify.Model.Entity.SkillsTools;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceSkillDto {

    private Long id;
    private SkillsTools skillsTools;
    private int experienceRate;
    @JsonFormat(pattern="yyyy")
    private Date experienceYears;
    private String additionalInfo;
}
