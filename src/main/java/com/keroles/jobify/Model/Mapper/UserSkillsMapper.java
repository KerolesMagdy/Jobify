package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.UserSkillsDto;
import com.keroles.jobify.Model.Entity.UserSkills;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {ExperienceSkillMapper.class,ExperienceLanguageMapper.class})
public interface UserSkillsMapper {
    
    UserSkillsDto mapToDto(UserSkills userSkills);
    List<UserSkillsDto> mapToDto(List<UserSkills> userSkillsList);
}
