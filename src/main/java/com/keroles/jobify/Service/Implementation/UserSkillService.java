package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.UserSkillsDto;
import com.keroles.jobify.Model.Entity.UserEducation;
import com.keroles.jobify.Model.Entity.UserSkills;
import com.keroles.jobify.Model.Mapper.UserSkillsMapper;
import com.keroles.jobify.Repository.UserSkillsRepo;
import com.keroles.jobify.Service.Operation.UserSkillServiceOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserSkillService implements UserSkillServiceOp {

    @Autowired
    private UserSkillsRepo userSkillsRepo;
    @Autowired
    private UserSkillsMapper userSkillsMapper;
    @Autowired
    private Environment environment;
    @Override
    public UserSkillsDto getByEmail(String email) {
        Optional<UserSkills> userSkills = userSkillsRepo.findByEmail(email);
        if (!userSkills.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.skills.not_found"));
        return userSkillsMapper.mapToDto(userSkills.get());

    }

    @Transactional
    @Override
    public UserSkillsDto save(String email) {
        Optional<UserSkills> userSkills = userSkillsRepo.findByEmail(email);
        if (userSkills.isPresent())
            throw new GlobalObjectFoundException(environment.getProperty("validate.message.user.skills.found"));
        return userSkillsMapper.mapToDto(userSkillsRepo.save(new UserSkills(null,email,null,null)));
    }

    @Override
    public String updateEmail(String old_email,String new_email) {
        if (userSkillsRepo.updateEmail( old_email, new_email)==0)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.skills.not_found"));
        else
            return "email updated successfully";
    }

    @Transactional
    @Override
    public String delete(String email) {
        Optional<UserSkills> retrievedUserSkills = userSkillsRepo.findByEmail(email);
        if (!retrievedUserSkills.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.skills.not_found"));
        userSkillsRepo.delete(retrievedUserSkills.get());
        return "user skills deleted successfully";

    }
}
