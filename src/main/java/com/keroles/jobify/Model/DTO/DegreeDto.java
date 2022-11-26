package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.keroles.jobify.Model.Entity.Country;
import com.keroles.jobify.Model.Entity.DegreeLevel;
import com.keroles.jobify.Model.Entity.UserEducation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DegreeDto {

    private Long id;
    private DegreeLevel degreeLevel;
    private Country country;
    private String university;
    private String field_study;
    @JsonFormat(pattern = "yyyy")
    private Date startYear;
    @JsonFormat(pattern = "yyyy")
    private Date endYear;
    private String studiedSubjects;
    private String additionalInfo;
}
