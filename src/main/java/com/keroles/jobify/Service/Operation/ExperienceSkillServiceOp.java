package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.ExperienceSkillDto;
import com.keroles.jobify.Model.Entity.ExperienceSkill;

public interface ExperienceSkillServiceOp {

    ExperienceSkillDto save(ExperienceSkill experienceSkill);
    ExperienceSkillDto update(ExperienceSkill experienceSkill);
    String delete(long id);


}
