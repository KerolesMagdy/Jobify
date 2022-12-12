package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Otp.OtpExpireException;
import com.keroles.jobify.Exception.Exceptions.User.UserDuplicateException;
import com.keroles.jobify.Exception.Exceptions.User.UserRegistrationFailedException;
import com.keroles.jobify.Exception.Exceptions.User.UserRoleDuplicateException;
import com.keroles.jobify.Mail.MailService;
import com.keroles.jobify.Media.MediaFile;
import com.keroles.jobify.Media.MediaService;
import com.keroles.jobify.Model.Entity.Media;
import com.keroles.jobify.Repository.MediaRepo;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Custom.UserForm;
import com.keroles.jobify.Model.Entity.UserOtp;
import com.keroles.jobify.Model.Entity.UserRole;
import com.keroles.jobify.Model.Entity.Users;
import com.keroles.jobify.Model.Mapper.UserMapper;
import com.keroles.jobify.Model.Custom.UsersDetails;
import com.keroles.jobify.Repository.UsersRepo;
import com.keroles.jobify.Sec.AuthFilter.AuthFilterUtil;
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
import java.util.List;

import static com.keroles.jobify.Media.MediaService.ImageSection.USER_IMAGE;

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
    MailService mailService;
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
    @Autowired
    private MediaRepo mediaRepo;
    @Override
    public UsersDetails loadUserByUsername(String username) {
        Users users=usersRepo.findByEmail(username.toCharArray());
        return users==null? null: new UsersDetails(users);
    }

    @Transactional
    @Override
    public Users saveUser(UserForm form) {

        if (usersRepo.findByEmail(form.getEmail())!=null) {
            throw new UserDuplicateException();
        }
        Users user= userMapper.mapRegisterFormToUser(form);
        List<UserRole> userRoles=new ArrayList<>();
        userRoles.add(userRoleService.getByRoleName("USER".toCharArray()));
        user.setUserRoles(userRoles);
        user.setPassword(passwordEncoder.encode(java.nio.CharBuffer.wrap(user.getPassword())).toCharArray());
        return usersRepo.save(user);
    }
    @Override
    public UserDto register(UserForm form) {
        Users user=saveUser(form);
        if (user!=null) {
//            sendVerificationEmail(user.getEmail());
            UserDto userDto = userMapper.mapUserToDto(user);
            List<char[]> authorities = new ArrayList<>();
            user.getUserRoles().forEach(userRole -> authorities.add(userRole.getRoleName()));
            userDto.setToken(tokenUtils.generateCompositeToken(userDto.getEmail(),authorities, AuthFilterUtil.UserType.USER));
            return userDto;
        }
        else throw new UserRegistrationFailedException();
    }
    @Transactional
    @Override
    public void saveRoleToUser(char[] user_name, char[] role_name) {
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
    public Users getUserByName(char[] user_name) {
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
    public UserDtoWithoutToken updateUserNameByEmail(char[] name, char[] email) {
        Users searchUser=usersRepo.findByEmail(email);
        if (searchUser==null) {
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        }
        searchUser.setFullName(name);
        return userMapper.mapUserToDtoWithoutToken(usersRepo.save(searchUser));
    }

    @Transactional
    @Override
    public String updateUserPassByEmail(char[] pass, char[] email) {
        Users searchUser=usersRepo.findByEmail(email);
        if (searchUser==null) {
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        }

        return usersRepo.updateUserPasswordByEmail(
                passwordEncoder.encode(java.nio.CharBuffer.wrap(pass)).toCharArray(),email)>0?
                "password reset successfully":"something went wrong";
    }

    @Transactional
    @Override
    public String sendVerificationEmail(char[] mailTo) {
        if (usersRepo.findByEmail(mailTo)==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
        return userOtpService.sendOtpToVerifyEmail(
                mailTo,
                "Verification link".toCharArray(),
                "http://localhost:8045/u/v".toCharArray()

        );
    }

    @Transactional
    @Override
    public String verifyEmail(char[] mailTo, int otp) {
        if (usersRepo.findByEmail(mailTo)==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));

        UserOtp userOtp=userOtpService.getUserOtp(mailTo);

        if(userOtp==null)
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.otp.not_found"));

        if(userOtp.getOtp()!=(otp)) throw new OtpExpireException();

        usersRepo.enableUser(mailTo,true);

        return "email verified successfully";
    }

    @Override
    public String deleteCredentialUser(char[] email) {
        return deleteUserWithExtensions(email);
    }

    @Transactional
    @Override
    public String deleteUserWithExtensions(char[] email) {
        if(usersRepo.removeByEmail(email)==1){
            userGeneralInfoService.remove(email);
            userCareerInterestsService.deleteByEmail(email);
            userExperienceService.deleteByEmail(email);
            userEducationService.deleteUserEducation(email);
            return "user deleted successfully";
        }
        throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.not_found"));
    }

    @Transactional
    @Override
    public Media uploadProfileImg(MultipartFile image, long id) {
        Media media=mediaService.storeUserImage(image,id, USER_IMAGE);
        media=mediaRepo.save(media);
        usersRepo.updateUserImageById(media,id);
        return media;
    }

    @Override
    public byte[] loadProfileImg(long id, String imageName) {
        return mediaService.reStoreImage(id,imageName);
    }

    @Override
    public Media uploadUserCv(MultipartFile cv, long id) {
        Media media=mediaService.storeCv(cv,id);
        media=mediaRepo.save(media);
        usersRepo.updateUserCvById(media,id);
        return media;
    }

    @Override
    public MediaFile downloadUserCv(long userId, String fileName, String fileType) {
        return mediaService.downloadCv(userId,fileName,fileType);
    }

}
