package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.EmployerDto;
import com.keroles.jobify.Model.Entity.Employer;
import com.keroles.jobify.Model.Entity.Media;
import org.springframework.web.multipart.MultipartFile;

public interface EmployerServiceOp {

    Employer saveEmployer(Employer employer);
    EmployerDto register(Employer employer);
    EmployerDto findEmployerByEmail(char[] email);
    String deleteEmployerByEmail(char[] email);
    String updateEmployerPassById(char[] email,char[] newPassword);
    Media uploadEmployerLogoById( MultipartFile image,long employerId);
    int changeEmployerState(char[] email,boolean enable);
    String sendVerificationEmail(char[] mailTo);
    String verifyEmail(char[] mailTo, int otp);

}
