package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.UserEducationDto;
import com.keroles.jobify.Model.Entity.DegreeLevel;
import com.keroles.jobify.Model.Entity.UserEducation;
import com.keroles.jobify.Model.Mapper.UserEducationMapper;
import com.keroles.jobify.Repository.UserEducationRepo;
import com.keroles.jobify.Service.Operation.UserEducationServicecOp;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserEducationService implements UserEducationServicecOp {
    private final UserEducationMapper userEducationMapper;
    private final UserEducationRepo userEducationRepo;
    private final Environment environment;

    public UserEducationService(UserEducationMapper userEducationMapper, UserEducationRepo userEducationRepo, Environment environment) {
        this.userEducationMapper = userEducationMapper;
        this.userEducationRepo = userEducationRepo;
        this.environment = environment;
    }

    @Transactional
    @Override
    public UserEducationDto getByEmail(String email) {
        Optional<UserEducation> userEducation = userEducationRepo.findByEmail(email);
        if (!userEducation.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.education.not_found"));
        return userEducationMapper.mapToDto(userEducation.get());
    }
    @Override
    public String updateCurrentDegreeLevel(long level_id, String email) {
        DegreeLevel level=new DegreeLevel(level_id,null);
        if (userEducationRepo.updateCurrentLevel(level,email)==0) {
            userEducationRepo.save(new UserEducation(null,email,level,null,null,null));
            return "current level created successfully";
        }
        else
            return "current level updated successfully";
    }

    @Override
    public String updateEmail(String old_email,String new_email) {
        if (userEducationRepo.updateEmail( old_email, new_email)==0)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.education.not_found"));
        else
            return "email updated successfully";
    }

    @Transactional
    @Override
    public String deleteUserEducation(String email) {
        Optional<UserEducation> retrievedEducation = userEducationRepo.findByEmail(email);
        if (!retrievedEducation.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.education.not_found"));
        userEducationRepo.delete(retrievedEducation.get());
        return "degree deleted successfully";

    }
}
