package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Custom.UserForm;
import com.keroles.jobify.Model.Entity.Users;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserServiceOp {
    Users saveUser(UserForm form);
    UserDtoWithoutToken updateUserNameByEmail(String name, String email);
    String updateUserPassByEmail(String pass, String email);
    void saveRoleToUser(String user_name, String role_name);
    Users getUserByName(String user_name);
    List<UserDtoWithoutToken> getUsers();
    UserDto register(UserForm form);
    String sendVerificationEmail(String mailTo);
    String verifyEmail(String mailTo, int otp);

    String deleteCredentialUser(String email);
    String deleteUserWithExtensions(String email);
    String uploadProfileImg(MultipartFile image, long id);

}
