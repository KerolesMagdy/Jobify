package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.Custom.SavePrevExperienceForm;
import com.keroles.jobify.Model.DTO.PreviousExperienceDto;
import com.keroles.jobify.Model.DTO.UserExperienceDto;
import com.keroles.jobify.Model.Entity.PreviousExperience;
import com.keroles.jobify.Model.Entity.UserExperience;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(uses = {PreviousExperienceMapper.class})
public interface UserExperienceMapper {

    UserExperienceDto mapToUserExperienceDto(UserExperience userExperience);
    List<UserExperienceDto> mapToUserExperienceDto(List<UserExperience> userExperienceList);

}
