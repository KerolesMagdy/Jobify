package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExperienceSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "skills_tools_id")
    @NotNull(message = "skill must not be null")
    private SkillsTools skillsTools;
    @Max(value = 5,message = "experience rate must be from 0 to 5")
    @Min(value = 0,message = "experience rate must be from 0 to 5")
    @NotNull(message = "experience rate must not be null")
    private int experienceRate;
    @JsonFormat(pattern="yyyy")
    @NotNull(message = "years of experience must not be null")
    private Date experienceYears;
    private String additionalInfo;
    @ManyToOne
    @JoinColumn(name = "user_skills_id")
    @JsonBackReference(value = "userskills-experienceskill")
    @NotNull(message = "user in experienced skill must not be null")
    private UserSkills userSkills;
}
