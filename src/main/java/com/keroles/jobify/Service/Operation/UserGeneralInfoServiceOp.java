package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.UserGeneralInfo;

public interface UserGeneralInfoServiceOp {

    UserGeneralInfo save(UserGeneralInfo userInfo);
    UserGeneralInfo update(UserGeneralInfo userInfo);
    UserGeneralInfo getInfoCredentialByEmail(char[] email);
    UserGeneralInfo getByEmail(char[] email);
    int remove(char[] email);
}
