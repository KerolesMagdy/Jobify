package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keroles.jobify.Model.Entity.ExperienceLanguage;
import com.keroles.jobify.Model.Entity.ExperienceSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSkillsDto {
    private Long id;
    private char[] email;
    private List<ExperienceSkillDto> experienceSkills;
    private List<ExperienceLanguageDto>  experienceLanguages;
}
