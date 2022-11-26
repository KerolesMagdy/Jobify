package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Exception.Exceptions.User.UserOpNotAuthException;
import com.keroles.jobify.Mail.MailServiceImpl;
import com.keroles.jobify.Mail.Model.SimpleMail;
import com.keroles.jobify.Model.Entity.UserOtp;
import com.keroles.jobify.Repository.UserOtpRepo;
import com.keroles.jobify.Repository.UsersRepo;
import com.keroles.jobify.Sec.Otp.Otp;
import com.keroles.jobify.Sec.Otp.OtpUtil;
import com.keroles.jobify.Service.Operation.UserOtpServiceOp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserOtpService implements UserOtpServiceOp {
    @Autowired
    private UserOtpRepo userOtpRepo;
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    MailServiceImpl mailService;
    @Autowired
    Environment environment;

    @Override
    public int generateOtp(String email) {
        Otp otp=otpUtil.generateRandomOtp();
        UserOtp userOtp=getUserOtp(email);
        if(userOtp!=null) {
            if(otpUtil.isOtpExpired(userOtp.getExpired())){
                userOtp.setOtp(otp.getOtp());
                userOtp.setExpired(otp.getExpired());
                userOtpRepo.save(userOtp);
            }
        }
        else
            userOtp=userOtpRepo.save(UserOtp.builder()
                    .email(email)
                    .otp(otp.getOtp())
                    .expired(otp.getExpired())
                    .build());
        return userOtp.getOtp();
    }
    @Transactional
    @Override
    public String sendOtpToUserByEmail(String mailTo) {
        if (usersRepo.findByEmail(mailTo)==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        if (otpUtil.isOtpExpired(userOtpRepo.findByEmail(mailTo).getExpired()))
         return "user previous otp still not expired";
        else if (mailService.sendSimpleMail(
                SimpleMail
                        .builder()
                        .recipient(mailTo)
                        .msgBody("your otp is : "+generateOtp(mailTo))
                        .subject("Verification Code").build())
        )
            return "Mail sent Successfully check your mail";
        else return "Something went wrong try again late";
    }
    @Override
    public UserOtp getUserOtp(String email) {
        UserOtp userOtp= userOtpRepo.findByEmail(email);
        if (userOtp==null)
            return null;
        return userOtp;
    }    @Override
    public UserOtp getCredentialUserOtp(String email) {
        if (!checkIdentityAuthOp(email))
            throw new UserOpNotAuthException();
        UserOtp userOtp= userOtpRepo.findByEmail(email);
        if (userOtp==null)
            return null;
        return userOtp;
    }


    boolean checkIdentityAuthOp(String email){
        return SecurityContextHolder.getContext().getAuthentication().getName().equals(email);
    }
}
