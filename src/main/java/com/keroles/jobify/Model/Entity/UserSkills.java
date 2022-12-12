package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Email
    @Column(unique = true)
    private char[] email;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="userSkills",cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "userskills-experienceskill")
    private List<ExperienceSkill> experienceSkills;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="userSkills", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "userskills-experiencelanguage")
    private List<ExperienceLanguage>  experienceLanguages;

}
