package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Model.DTO.ExperienceSkillDto;
import com.keroles.jobify.Model.Entity.ExperienceLanguage;
import com.keroles.jobify.Model.Entity.ExperienceSkill;
import com.keroles.jobify.Model.Mapper.ExperienceSkillMapper;
import com.keroles.jobify.Repository.ExperienceSkillRepo;
import com.keroles.jobify.Service.Operation.ExperienceSkillServiceOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ExperienceSkillService implements ExperienceSkillServiceOp {
    @Autowired
    private ExperienceSkillRepo experienceSkillRepo;
    @Autowired
    private ExperienceSkillMapper experienceSkillMapper;
    @Autowired
    private Environment environment;

    @Override
    public ExperienceSkillDto save(ExperienceSkill experienceSkill) {
        return experienceSkillMapper.mapToDto(experienceSkillRepo.save(experienceSkill));
    }

    @Override
    public ExperienceSkillDto update(ExperienceSkill experienceSkill) {
        if (experienceSkill.getId()==null)
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.user.experience.skill.not_found"));
        Optional<ExperienceSkill> retrievedExperienceSkill=experienceSkillRepo.findById(experienceSkill.getId());
        if (!retrievedExperienceSkill.isPresent())
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.user.experience.skill.not_found"));
        return experienceSkillMapper.mapToDto(experienceSkillRepo.save(experienceSkill));    }

    @Override
    public String delete(long id) {
        Optional<ExperienceSkill> retrievedExperienceSkill=experienceSkillRepo.findById(id);
        if (!retrievedExperienceSkill.isPresent())
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.user.experience.skill.not_found"));
        experienceSkillRepo.delete(retrievedExperienceSkill.get());
        return "experience skill deleted successfully";

    }
}
