package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.Custom.SavePrevExperienceForm;
import com.keroles.jobify.Model.DTO.PreviousExperienceDto;
import com.keroles.jobify.Model.Entity.PreviousExperience;
import com.keroles.jobify.Model.Mapper.PreviousExperienceMapper;
import com.keroles.jobify.Repository.PreviousExperienceRepo;
import com.keroles.jobify.Service.Operation.PreviousExperienceServiceOp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PreviousExperienceService implements PreviousExperienceServiceOp {

    private final PreviousExperienceRepo experienceRepo;
    private final PreviousExperienceMapper previousExperienceMapper;
    private final Environment environment;

    public PreviousExperienceService(PreviousExperienceRepo experienceRepo, PreviousExperienceMapper previousExperienceMapper, Environment environment) {
        this.experienceRepo = experienceRepo;
        this.previousExperienceMapper = previousExperienceMapper;
        this.environment = environment;
    }

    @Override
    public PreviousExperienceDto save(SavePrevExperienceForm experienceForm) {
        return previousExperienceMapper.mapToPreviousExperienceDto(
                experienceRepo.save(previousExperienceMapper.mapToPreviousExperience(experienceForm))
        );
    }


    @Override
    public PreviousExperienceDto update(PreviousExperience experience) {
        PreviousExperience previousExperience=experienceRepo.findById(experience.getId()).get();
        if (previousExperience==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.previous_experience.not_found"));
        return previousExperienceMapper.mapToPreviousExperienceDto(experienceRepo.save(experience));
    }

    @Override
    public PreviousExperienceDto getByPreviousExperienceId(long previousExperienceId) {
        try {

            Optional<PreviousExperience> previousExperience = experienceRepo.findById(previousExperienceId);
            if (previousExperience == null)
                throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.previous_experience.not_found"));
            return previousExperienceMapper.mapToPreviousExperienceDto(previousExperience.get());
        }catch (Exception e){
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.previous_experience.not_found"));
        }
    }

    @Override
    public List<PreviousExperienceDto> getByUserExperienceId(long userExperienceId) {
        List<PreviousExperience> previousExperiences=experienceRepo.findByUserExperience_Id(userExperienceId);
        log.error(previousExperiences.size()+"");
        if (previousExperiences.isEmpty())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.experience.not_found"));

        return previousExperienceMapper.mapToListPreviousExperienceDto(previousExperiences);
    }

    @Override
    public String deleteByPreviousExperienceId(long previousExperienceId) {
        try {
            experienceRepo.deleteById(previousExperienceId);
        }catch (Exception e){
            return "something went wrong try again later";
        }
        return "previous experience deleted successfully";
    }

    @Override
    public String deleteByUserExperienceId(long userExperienceId) {
        try {
            experienceRepo.deleteByUserExperience_Id(userExperienceId);
        }catch (Exception e){
            return "something went wrong try again later";
        }
        return "previous experience deleted successfully";
    }
}
