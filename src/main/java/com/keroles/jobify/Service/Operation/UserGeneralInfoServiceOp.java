package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.UserGeneralInfo;

public interface UserGeneralInfoServiceOp {

    UserGeneralInfo save(UserGeneralInfo userInfo);
    UserGeneralInfo update(UserGeneralInfo userInfo);
    UserGeneralInfo getInfoCredentialByEmail(String email);
    UserGeneralInfo getByEmail(String email);
    int remove(String email);
}
