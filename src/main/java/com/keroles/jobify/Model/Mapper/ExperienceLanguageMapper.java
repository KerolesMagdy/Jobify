package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.ExperienceLanguageDto;
import com.keroles.jobify.Model.Entity.ExperienceLanguage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ExperienceLanguageMapper {
    ExperienceLanguageDto mapToDto(ExperienceLanguage experienceLanguage);
    List<ExperienceLanguageDto> mapToDto(List<ExperienceLanguage> experienceLanguage);
}
