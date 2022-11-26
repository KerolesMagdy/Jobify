package com.keroles.jobify.Model.Custom;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.keroles.jobify.Model.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavePrevExperienceForm {

    @NotBlank(message = "you must pass valid title")
    private String jobTitle;
    @NotBlank(message = "you must pass valid company name")
    private String companyName;
    @NotNull(message = "you must pass job type")
    private JobType jobType;
    @NotNull(message = "you must pass job category")
    private JobCategory jobCategory;
    @NotNull(message = "you must pass start date")
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date startDate;
    @Nullable
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date endDate;
    @Nullable
    private String description;
    @Nullable
    private CareerLevel careerLevel;
    @Nullable
    private Country country;
    @Nullable
    private int companyEmpFrom;
    @Nullable
    private int companyEmpTo;
    @Nullable
    private JobCategory industry;
    @Nullable
    private String companyWebsite;
    @Nullable
    private String achievements;
    @NotNull(message = "you must pass start salary")
    private double startSalary;
    @NotNull(message = "you must pass end salary")
    private double endSalary;
    @NotNull(message = "you must pass type of currency")
    private String currency;
    private boolean salaryEnable=false;
    @NotNull(message = "you must pass user experience id")
    private UserExperience userExperience;
}
