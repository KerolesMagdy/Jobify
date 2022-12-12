package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NotNull(message = "The given email must not be null")
    private char[] email;
    @OneToOne
    @JoinColumn(name = "degree_level_id")
    @NotNull(message = "The given current degree level must not be null")
    private DegreeLevel currentDegreeLevel;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="userEducation",cascade = CascadeType.REMOVE )
    @JsonManagedReference(value = "usereducation-degree")
    @Nullable
    private List<Degree> degrees;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="userEducation" ,cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "usereducation-certificate")
    @Nullable
    private List<Certificate> certificates;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="userEducation",cascade = CascadeType.REMOVE )
    @JsonManagedReference(value = "usereducation-trainingCourses")
    @Nullable
    private List<TrainingCourses> trainingCourses;
}
