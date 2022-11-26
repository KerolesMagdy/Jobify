package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.UserCareerInterests;

public interface UserCareerInterestsServiceOp {
    UserCareerInterests save(UserCareerInterests userCareerInterests);
    UserCareerInterests update(UserCareerInterests userCareerInterests);
    int deleteByEmail(String email);
    UserCareerInterests getCareerInterestsByEmail(String email);
}
