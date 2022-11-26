package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name =  "jobdetails_careerlevel"
            ,joinColumns = @JoinColumn(name = "job_details_id")
            ,inverseJoinColumns = @JoinColumn(name = "levels_Id"))
    private Set<CareerLevel> careerLevels=new HashSet<>();
    private double salary;
    @OneToOne
    @JoinColumn(name = "job_type_id")
    private JobType jobType;
    @OneToOne
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;
    @ManyToMany()
    @JoinTable(name =  "jobdetails_skillstools"
            ,joinColumns = @JoinColumn(name = "job_details_id")
            ,inverseJoinColumns = @JoinColumn(name = "skills_tools_Id"))
    private Set<SkillsTools> skillsTools=new HashSet<>();
    private String jobDescription;
    private String jobRequirements;
    @Max(value = 50,message = "${validate.message.experience.years.max}")
    private int experienceYearsFrom;
    @Min(value = 1,message = "${validate.message.experience.years.max}")
    private int experienceYearsTo;
}
