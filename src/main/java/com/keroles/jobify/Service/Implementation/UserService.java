package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Otp.OtpExpireException;
import com.keroles.jobify.Exception.Exceptions.User.UserDuplicateException;
import com.keroles.jobify.Exception.Exceptions.User.UserRoleDuplicateException;
import com.keroles.jobify.Mail.MailServiceImpl;
import com.keroles.jobify.Mail.Model.SimpleMail;
import com.keroles.jobify.Media.MediaService;
import com.keroles.jobify.Sec.Token.Model.CompositeToken;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Custom.UserForm;
import com.keroles.jobify.Model.Entity.UserOtp;
import com.keroles.jobify.Model.Entity.UserRole;
import com.keroles.jobify.Model.Entity.Users;
import com.keroles.jobify.Model.Mapper.UserMapper;
import com.keroles.jobify.Model.UsersDetails;
import com.keroles.jobify.Repository.UsersRepo;
import com.keroles.jobify.Sec.Token.Util.TokenUtils;
import com.keroles.jobify.Service.Operation.UserServiceOp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService implements UserDetailsService , UserServiceOp {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserOtpService userOtpService;
    @Autowired
    MailServiceImpl mailService;
    @Autowired
    UserGeneralInfoService userGeneralInfoService;
    @Autowired
    UserCareerInterestsServiceImpl userCareerInterestsService;
    @Autowired
    private UserEducationService userEducationService;
    @Autowired
    Environment environment;
    @Autowired
    UserExperienceService userExperienceService;
    @Autowired
    private MediaService mediaService;
    @Override
    public UsersDetails loadUserByUsername(String username) {
        Users users=usersRepo.findByEmail(username);
        if(users==null) {
            return null;
        }
        return new UsersDetails(users);
    }

    @Transactional
    @Override
    public Users saveUser(UserForm form) {

        if (usersRepo.findByEmail(form.getEmail())!=null) {
            throw new UserDuplicateException();
        }
        Users user= userMapper.mapRegisterFormToUser(form);
        Set<UserRole> userRoles=new HashSet<>();
        userRoles.add(userRoleService.getByRoleName("USER"));
        user.setUserRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepo.save(user);
    }

    @Transactional
    @Override
    public void saveRoleToUser(String user_name, String role_name) {
        Users users=getUserByName(user_name);
        UserRole userRole=userRoleService.getByRoleName(role_name);
        if (userRole==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.role.not_found"));
        if(users.getUserRoles().contains(userRole))
            throw new UserRoleDuplicateException();
        users.getUserRoles().add(userRole);
        usersRepo.save(users);
    }

    @Override
    public Users getUserByName(String user_name) {
        Users user=usersRepo.findByEmail(user_name);
        if (user==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        return user;
    }

    @Override
    public  List<UserDtoWithoutToken> getUsers() {
        return userMapper.mapUserToDtoWithoutToken(usersRepo.findAll());
    }
    
    @Transactional
    @Override
    public UserDtoWithoutToken updateUserNameByEmail(String name, String email) {
        Users searchUser=usersRepo.findByEmail(email);
        if (searchUser==null) {
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        }
        searchUser.setFullName(name);
        return userMapper.mapUserToDtoWithoutToken(usersRepo.save(searchUser));
    }

    @Transactional
    @Override
    public String updateUserPassByEmail(String pass, String email) {
        Users searchUser=usersRepo.findByEmail(email);
        if (searchUser==null) {
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        }

        return usersRepo.updateUserPasswordByEmail(passwordEncoder.encode(pass),email)>0?"password reset successfully":"something went wrong";
    }

    @Override
    public UserDto register(UserForm form) {
        Users user=saveUser(form);
//        if (user!=null)
//            sendVerificationEmail(user.getEmail());
        UserDto userDto =userMapper.mapUserToDto(user);
        List<String> authorities=new ArrayList<>();
        user.getUserRoles().forEach(userRole -> authorities.add(userRole.getRoleName()));

        CompositeToken tokenDto= CompositeToken.builder().build();
        tokenDto.setAccessToken(tokenUtils.generateToken(userDto.getEmail(),authorities,tokenUtils.getACCESS_TOKEN_VALIDITY()));
        tokenDto.setRefreshToken(tokenUtils.generateToken(userDto.getEmail(),authorities,tokenUtils.getREFRESH_TOKEN_VALIDITY()));
        userDto.setToken(tokenDto);

        return userDto;
    }

    @Transactional
    @Override
    public String sendVerificationEmail(String mailTo) {
        if (usersRepo.findByEmail(mailTo)==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        if (mailService.sendSimpleMail(
                SimpleMail
                        .builder()
                        .recipient(mailTo)
                        .msgBody("verification link is : http://localhost:8045/u/v/"+mailTo+"/"+userOtpService.generateOtp(mailTo))
                        .subject("Verification link").build())
        )
            return "Mail sent Successfully check your mail";
        else return "Something went wrong try again late";
    }

    @Transactional
    @Override
    public String verifyEmail(String mailTo, int otp) {
        if (usersRepo.findByEmail(mailTo)==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));

        UserOtp userOtp=userOtpService.getUserOtp(mailTo);

        if(userOtp==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.otp.not_found"));

        if(userOtp.getOtp()!=(otp)) throw new OtpExpireException();

        usersRepo.enableUser(mailTo);

        return "email verified successfully";
    }

    @Override
    public String deleteCredentialUser(String email) {
        return deleteUserWithExtensions(email);
    }

    @Transactional
    @Override
    public String deleteUserWithExtensions(String email) {
        if(usersRepo.removeByEmail(email)==1){
            userGeneralInfoService.remove(email);
            userCareerInterestsService.deleteByEmail(email);
            userExperienceService.deleteByEmail(email);
            userEducationService.deleteUserEducation(email);
            return "user deleted successfully";
        }
        throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
    }

    @Override
    public String uploadProfileImg(MultipartFile image, long id) {
        String file_path=mediaService.storeImage(image,id);
        if (file_path ==null)return "something went wrong try again later";
        return file_path;
    }

}
