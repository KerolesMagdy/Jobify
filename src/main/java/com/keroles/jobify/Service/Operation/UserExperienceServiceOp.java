package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.UserExperienceDto;

public interface UserExperienceServiceOp {

    UserExperienceDto getByEmail(char[] email);
    String updateUserUserExperienceYears(char[] email, int years);
    void deleteById(long id);
    void deleteByEmail(char[] email);

}
