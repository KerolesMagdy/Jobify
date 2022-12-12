package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.CareerInterests.UserCareerInterestsIdException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.Entity.UserCareerInterests;
import com.keroles.jobify.Repository.UserCareerInterestsRepo;
import com.keroles.jobify.Service.Operation.UserCareerInterestsServiceOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserCareerInterestsServiceImpl implements UserCareerInterestsServiceOp {
    @Autowired
    private UserCareerInterestsRepo userCareerInterestsRepo;
    @Autowired
    Environment environment;
    @Override
    public UserCareerInterests save(UserCareerInterests userCareerInterests) {
        UserCareerInterests careerInterests=userCareerInterestsRepo.findByEmail(userCareerInterests.getEmail());
        if(careerInterests==null)
            return userCareerInterestsRepo.save(userCareerInterests);

        throw new GlobalObjectFoundException(environment.getProperty("validate.message.user.career_interests.found"));
    }

    @Override
    public UserCareerInterests update(UserCareerInterests userCareerInterests) {
        UserCareerInterests careerInterests=userCareerInterestsRepo.findByEmail(userCareerInterests.getEmail());
        if(careerInterests.getId()!=userCareerInterests.getId())
            throw new UserCareerInterestsIdException();
        if(careerInterests!=null ){
            careerInterests.updatePropertiesExceptId(userCareerInterests);
            return userCareerInterestsRepo.save(userCareerInterests);
        }
        throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.career_interests.not_found"));
    }

    @Override
    public int deleteByEmail(char[] email) {
       return userCareerInterestsRepo.removeByEmail(email);

    }

    @Override
    public UserCareerInterests getCareerInterestsByEmail(char[] email) {
        UserCareerInterests userCareerInterests=  userCareerInterestsRepo.findByEmail(email);
        if(userCareerInterests==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.career_interests.not_found"));
        return userCareerInterests;
    }
}
