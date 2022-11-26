package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.ExperienceLanguageDto;
import com.keroles.jobify.Model.Entity.ExperienceLanguage;

public interface ExperienceLanguageServiceOp {

    ExperienceLanguageDto save(ExperienceLanguage experienceLanguage);
    ExperienceLanguageDto update(ExperienceLanguage experienceLanguage);
    String delete(long id);


}
