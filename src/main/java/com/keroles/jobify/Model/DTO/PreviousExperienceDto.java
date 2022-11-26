package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.keroles.jobify.Model.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreviousExperienceDto {

    private Long id;
    private String jobTitle;
    private String companyName;
    private JobType jobType;
    private JobCategory jobCategory;
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date endDate;
    private String description;
    private CareerLevel careerLevel;
    private Country country;
    private int companyEmpFrom;
    private int companyEmpTo;
    private JobCategory industry;
    private String companyWebsite;
    private String achievements;
    private double startSalary;
    private double endSalary;
    private String currency;
    private boolean salaryEnable;
}
