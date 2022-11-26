package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.Custom.SavePrevExperienceForm;
import com.keroles.jobify.Model.DTO.PreviousExperienceDto;
import com.keroles.jobify.Model.Entity.PreviousExperience;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PreviousExperienceMapper {
    PreviousExperience mapToPreviousExperience(SavePrevExperienceForm savePrevExperienceForm);
    PreviousExperienceDto mapToPreviousExperienceDto(PreviousExperience previousExperience);
    List<PreviousExperienceDto> mapToListPreviousExperienceDto(List<PreviousExperience> previousExperienceList);

}
