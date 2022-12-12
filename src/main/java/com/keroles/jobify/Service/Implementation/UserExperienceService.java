package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.UserExperienceDto;
import com.keroles.jobify.Model.Entity.UserExperience;
import com.keroles.jobify.Model.Mapper.UserExperienceMapper;
import com.keroles.jobify.Repository.PreviousExperienceRepo;
import com.keroles.jobify.Repository.UserExperienceRepo;
import com.keroles.jobify.Service.Operation.UserExperienceServiceOp;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserExperienceService implements UserExperienceServiceOp {

    private final UserExperienceRepo userExperienceRepo;
    private final PreviousExperienceRepo previousExperienceRepo;
    private final Environment environment;
    private final UserExperienceMapper userExperienceMapper;

    public UserExperienceService(UserExperienceRepo userExperienceRepo, PreviousExperienceRepo previousExperienceRepo, Environment environment, UserExperienceMapper userExperienceMapper) {
        this.userExperienceRepo = userExperienceRepo;
        this.previousExperienceRepo = previousExperienceRepo;
        this.environment = environment;
        this.userExperienceMapper = userExperienceMapper;
    }

    @Override
    public UserExperienceDto getByEmail(char[] email) {
        Optional<UserExperience> userExperience=userExperienceRepo.findByEmail(email);
        if (!userExperience.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.experience.not_found"));
        return userExperienceMapper.mapToUserExperienceDto(userExperience.get());
    }

    @Transactional
    @Override
    public String updateUserUserExperienceYears(char[] email, int years) {
        Optional<UserExperience> experience=userExperienceRepo.findByEmail(email);
        if (!experience.isPresent()){
            if(userExperienceRepo.save(new UserExperience(null,email,years,new ArrayList<>()))==null)
                return "Something went wrong try again later";
            else
                return "user experience years created successfully";
        }
        experience.get().setWorkExperienceYears(years);
        if(userExperienceRepo.save(experience.get())!=null)
            return "user experience years updated successfully";
        return "Something went wrong try again later";
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        try {
            userExperienceRepo.deleteById(id);
//            previousExperienceRepo.deleteByUserExperience_Id(id);
        }catch (Exception e){
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.experience.not_found"));
        }
    }

    @Override
    public void deleteByEmail(char[] email) {
        userExperienceRepo.removeByEmail(email);
    }

}
