package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class PreviousExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String companyName;
    @OneToOne
    @JoinColumn(name = "job_type_id")
    private JobType jobType;
    @OneToOne
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;
    @NotNull
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date startDate;
    @Nullable
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date endDate;
    @Nullable
    private String description;
    @OneToOne
    @JoinColumn(name = "career_level_id")
    @Nullable
    private CareerLevel careerLevel;
    @OneToOne
    @JoinColumn(name = "country_id")
    @Nullable
    private Country country;
    @Nullable
    private int companyEmpFrom;
    @Nullable
    private int companyEmpTo;
    @OneToOne
    @JoinColumn(name = "industry_id")
    @Nullable
    private JobCategory industry;
    @Nullable
    private String companyWebsite;
    @Nullable
    private String achievements;
    @Nullable
    private double startSalary;
    @Nullable
    private double endSalary;
    @Nullable
    private String currency;
    @Nullable
    private boolean salaryEnable;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_experience_id")
    @JsonBackReference(value = "userexperience-previousexperience")
    @NotNull
    private UserExperience userExperience;
}
