package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Media.MediaFile;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Custom.UserForm;
import com.keroles.jobify.Model.Entity.Media;
import com.keroles.jobify.Model.Entity.Users;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserServiceOp {
    Users saveUser(UserForm form);
    UserDtoWithoutToken updateUserNameByEmail(char[] name, char[] email);
    String updateUserPassByEmail(char[] pass, char[] email);
    void saveRoleToUser(char[] user_name, char[] role_name);
    Users getUserByName(char[] user_name);
    List<UserDtoWithoutToken> getUsers();
    UserDto register(UserForm form);
    String sendVerificationEmail(char[] mailTo);
    String verifyEmail(char[] mailTo, int otp);

    String deleteCredentialUser(char[] email);
    String deleteUserWithExtensions(char[] email);
    Media uploadProfileImg(MultipartFile image, long id);
    byte[] loadProfileImg(long id, String imageName);

    Media uploadUserCv(MultipartFile cv, long id);
    MediaFile downloadUserCv(long userId, String fileName, String fileType);
}
