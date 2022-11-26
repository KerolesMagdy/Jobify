package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TrainingCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "The given name must not be null ")
    private String name;
    @NotNull
    private String organizationName;
    @NotNull(message = "The given date must not be null ")
    @JsonFormat(pattern = "yyyy-MM")
    private Date date;
    @Nullable
    private String additionalInfo;
    @ManyToOne
    @JoinColumn(name = "user_education_id")
    @JsonBackReference(value = "usereducation-trainingCourses")
    @NotNull(message = "The given user education must not be null ")
    private UserEducation userEducation;
}
