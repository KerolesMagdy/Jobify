package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Custom.SavePrevExperienceForm;
import com.keroles.jobify.Model.DTO.PreviousExperienceDto;
import com.keroles.jobify.Model.Entity.PreviousExperience;

import java.util.List;

public interface PreviousExperienceServiceOp {

    PreviousExperienceDto save(SavePrevExperienceForm previousExperience);
    PreviousExperienceDto update(PreviousExperience previousExperience);
    PreviousExperienceDto getByPreviousExperienceId(long previousExperienceId);
    List<PreviousExperienceDto> getByUserExperienceId(long userExperienceId);
    String deleteByPreviousExperienceId(long previousExperienceId);
    String deleteByUserExperienceId(long userExperienceId);
}
