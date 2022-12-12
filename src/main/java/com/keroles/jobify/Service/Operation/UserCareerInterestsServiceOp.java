package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.UserCareerInterests;

public interface UserCareerInterestsServiceOp {
    UserCareerInterests save(UserCareerInterests userCareerInterests);
    UserCareerInterests update(UserCareerInterests userCareerInterests);
    int deleteByEmail(char[] email);
    UserCareerInterests getCareerInterestsByEmail(char[] email);
}
