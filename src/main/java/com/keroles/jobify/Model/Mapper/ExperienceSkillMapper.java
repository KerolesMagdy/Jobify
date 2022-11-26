package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.ExperienceSkillDto;
import com.keroles.jobify.Model.Entity.ExperienceSkill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ExperienceSkillMapper {

    ExperienceSkillDto mapToDto(ExperienceSkill experienceSkill);
    List<ExperienceSkillDto> mapToDto(List<ExperienceSkill> experienceSkill);
}
