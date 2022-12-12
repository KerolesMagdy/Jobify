package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.UserSkillsDto;
import com.keroles.jobify.Model.Entity.UserEducation;
import com.keroles.jobify.Model.Entity.UserSkills;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserSkillServiceOp {


    UserSkillsDto getByEmail(char[] email);
    UserSkillsDto save(char[]  email);
    String updateEmail(char[] old_email,char[] new_email);
    String delete(char[] email);

}
