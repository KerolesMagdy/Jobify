package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.ExperienceLanguageDto;
import com.keroles.jobify.Model.Entity.Degree;
import com.keroles.jobify.Model.Entity.ExperienceLanguage;
import com.keroles.jobify.Model.Mapper.ExperienceLanguageMapper;
import com.keroles.jobify.Repository.ExperienceLanguageRepo;
import com.keroles.jobify.Service.Operation.ExperienceLanguageServiceOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExperienceLanguageService implements ExperienceLanguageServiceOp {

    @Autowired
    private ExperienceLanguageRepo experienceLanguageRepo;
    @Autowired
    private Environment environment;
    @Autowired
    private ExperienceLanguageMapper experienceLanguageMapper;

    @Override
    public ExperienceLanguageDto save(ExperienceLanguage experienceLanguage) {
        return experienceLanguageMapper.mapToDto(experienceLanguageRepo.save(experienceLanguage));
    }

    @Override
    public ExperienceLanguageDto update(ExperienceLanguage experienceLanguage) {
        if (experienceLanguage.getId()==null)
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.user.experience.language.not_found"));
        Optional<ExperienceLanguage> retrievedExperienceLanguage=experienceLanguageRepo.findById(experienceLanguage.getId());
        if (!retrievedExperienceLanguage.isPresent())
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.user.experience.language.not_found"));
        return experienceLanguageMapper.mapToDto(experienceLanguageRepo.save(experienceLanguage));

    }

    @Override
    public String delete(long id) {
        Optional<ExperienceLanguage> retrievedExperienceLanguage=experienceLanguageRepo.findById(id);
        if (!retrievedExperienceLanguage.isPresent())
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.user.experience.language.not_found"));
        experienceLanguageRepo.delete(retrievedExperienceLanguage.get());
        return "experience language deleted successfully";
    }
}
