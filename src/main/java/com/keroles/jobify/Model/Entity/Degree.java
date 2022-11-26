package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Degree{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "degree_level_id")
    @NotNull(message = "The given degree level must not be null ")
    private DegreeLevel degreeLevel;
    @OneToOne
    @JoinColumn(name = "country_id")
    @NotNull(message = "The given country must not be null ")
    private Country country;
    @Nullable
    private String university;
    @NotNull(message = "The given field(s) of study must not be null ")
    private String field_study;
    @NotNull(message = "The given start year must not be null ")
    @JsonFormat(pattern = "yyyy")
    private Date startYear;
    @Nullable
    @JsonFormat(pattern = "yyyy")
    private Date endYear;
    @Nullable
    private String studiedSubjects;
    @Nullable
    private String additionalInfo;
    @ManyToOne
    @JoinColumn(name = "user_education_id")
    @JsonBackReference(value = "usereducation-degree")
    @NotNull(message = "The given user education must not be null ")
    private UserEducation userEducation;
}
