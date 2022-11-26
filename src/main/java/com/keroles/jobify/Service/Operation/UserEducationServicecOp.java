package com.keroles.jobify.Service.Operation;


import com.keroles.jobify.Model.DTO.UserEducationDto;

public interface UserEducationServicecOp {
    UserEducationDto getByEmail(String email);
    String updateCurrentDegreeLevel(long level, String email);
    String updateEmail(String old_email,String new_email);
    String deleteUserEducation(String email);

}
