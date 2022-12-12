package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Employer.EmployerDuplicateException;
import com.keroles.jobify.Exception.Exceptions.Employer.EmployerRegistrationFailedException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Otp.OtpExpireException;
import com.keroles.jobify.Mail.MailService;
import com.keroles.jobify.Media.MediaService;
import com.keroles.jobify.Model.Custom.EmployerUserDetails;
import com.keroles.jobify.Model.DTO.EmployerDto;
import com.keroles.jobify.Model.Entity.Employer;
import com.keroles.jobify.Model.Entity.Media;
import com.keroles.jobify.Model.Entity.UserOtp;
import com.keroles.jobify.Model.Entity.UserRole;
import com.keroles.jobify.Model.Mapper.EmployerMapper;
import com.keroles.jobify.Model.Custom.UsersDetails;
import com.keroles.jobify.Repository.EmployerRepo;
import com.keroles.jobify.Repository.MediaRepo;
import com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil;
import com.keroles.jobify.Sec.Token.Util.TokenUtils;
import com.keroles.jobify.Service.Operation.EmployerServiceOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static com.keroles.jobify.Media.MediaService.ImageSection.EMPLOYER_IMAGE;
import static com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil.UserType.EMPLOYER;

@Service
public class EmployerService implements UserDetailsService,EmployerServiceOp{
    @Autowired
    private EmployerRepo employerRepo;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserOtpService userOtpService;
    @Autowired
    MailService mailService;
    @Autowired
    private MediaRepo mediaRepo;
    @Autowired
    private MediaService mediaService;
    @Autowired
    Environment environment;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private EmployerMapper employerMapper;

    @Override
    public EmployerUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employer>employer=employerRepo.findByEmail(username.toCharArray());
        return !employer.isPresent()? null:new EmployerUserDetails(employer.get());
    }

    @Transactional
    @Override
    public Employer saveEmployer(Employer employer) {
        if(employerRepo.findByEmail(employer.getEmail()).isPresent())
            throw new EmployerDuplicateException();
        List<UserRole> userRoles=new ArrayList();
        userRoles.add(userRoleService.getByRoleName("USER".toCharArray()));
        employer.setUserRoles(userRoles);
        employer.setPassword(passwordEncoder.encode(java.nio.CharBuffer.wrap(employer.getPassword())).toCharArray());
        return employerRepo.save(employer);
    }

    @Override
    public EmployerDto register(Employer employer) {
        Employer emp=saveEmployer(employer);
        if(emp!=null) {
            sendVerificationEmail(employer.getEmail());
            EmployerDto employerDto=employerMapper.mapToEmployerDto(emp);
            List<char[]> authorities = new ArrayList<>();
            emp.getUserRoles().forEach(userRole -> authorities.add(userRole.getRoleName()));
            employerDto.setToken(tokenUtils.generateCompositeToken(employerDto.getEmail(),authorities, EMPLOYER));
            return employerDto;

        }else throw new EmployerRegistrationFailedException();
    }

    @Override
    public EmployerDto findEmployerByEmail(char[] email) {
        Optional<Employer> employer=employerRepo.findByEmail(email);
        if(!employer.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.employer.not_found"));
        return employerMapper.mapToEmployerDto(employer.get());
    }

    @Override
    public String deleteEmployerByEmail(char[] email) {
        if(employerRepo.removeByEmail(email)==1)
            return "employer deleted successfully";
        throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.employer.not_found"));
    }

    @Transactional
    @Override
    public String updateEmployerPassById(char[] email, char[] newPassword) {
        if(!employerRepo.findByEmail(email).isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.employer.not_found"));
        return employerRepo.updateEmployerPassById(email,
                passwordEncoder.encode(java.nio.CharBuffer.wrap(newPassword)).toCharArray())>0?
                "password reset successfully":"something went wrong";

    }

    @Transactional
    @Override
    public Media uploadEmployerLogoById(MultipartFile image,long employerId) {
        Media media=mediaService.storeUserImage(image,employerId, EMPLOYER_IMAGE);
        media=mediaRepo.save(media);
        employerRepo.updateEmployerLogoById(employerId,media);
        return media;
    }

    @Override
    public int changeEmployerState(char[] email, boolean enable) {
        return employerRepo.changeEmployerState(email,enable);
    }

    @Transactional
    @Override
    public String sendVerificationEmail(char[] mailTo) {
        if (employerRepo.findByEmail(mailTo)==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        return userOtpService.sendOtpToVerifyEmail(
                mailTo,
                "Verification link".toCharArray(),
                "http://localhost:8045/e/v".toCharArray()

        );
    }

    @Transactional
    @Override
    public String verifyEmail(char[] mailTo, int otp) {
        if (!employerRepo.findByEmail(mailTo).isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.employer.not_found"));

        UserOtp userOtp=userOtpService.getUserOtp(mailTo);

        if(userOtp==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.otp.not_found"));

        if(userOtp.getOtp()!=(otp)) throw new OtpExpireException();

        employerRepo.changeEmployerState(mailTo,true);

        return "email verified successfully";
    }

}
