package com.keroles.jobify.Service.Operation;


import com.keroles.jobify.Model.DTO.UserEducationDto;

public interface UserEducationServicecOp {
    UserEducationDto getByEmail(char[] email);
    String updateCurrentDegreeLevel(long level, char[] email);
    String updateEmail(char[] old_email,char[] new_email);
    String deleteUserEducation(char[] email);

}
