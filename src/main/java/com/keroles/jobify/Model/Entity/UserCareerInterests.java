package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCareerInterests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email(message = "Not valid email")
    @NotNull(message = "You must write your email")
    @Column(unique = true)
    private char[] email;
    @OneToOne
    @JoinColumn(name = "career_level_id")
    private CareerLevel careerLevel;
    private double minSalary;
    private String countryLikeToWork;
    private boolean openToWork =true;

    public void updatePropertiesExceptId(UserCareerInterests careerInterests){
        this.careerLevel=careerInterests.getCareerLevel();
        this.minSalary=careerInterests.getMinSalary();
        this.countryLikeToWork=careerInterests.getCountryLikeToWork();
        this.openToWork=careerInterests.isOpenToWork();

    }
}
