package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.UserExperienceDto;

public interface UserExperienceServiceOp {

    UserExperienceDto getByEmail(String email);
    String updateUserUserExperienceYears(String email, int years);
    void deleteById(long id);
    void deleteByEmail(String email);

}
