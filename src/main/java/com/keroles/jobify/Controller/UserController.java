package com.keroles.jobify.Controller;

import com.keroles.jobify.Media.MediaService;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Custom.UserForm;
import com.keroles.jobify.Model.Entity.Users;
import com.keroles.jobify.Service.Implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping()
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public void getUserInfo(){
//        return ResponseEntity.ok().body(userService.getUserAfterAuthentication());
    }
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserForm form){
        return ResponseEntity.ok().body(userService.register(form));
    }
    @RequestMapping(value = "/token/refresh",method = RequestMethod.POST)
    public void saveRoleToUser(){

    }
    @RequestMapping(value = "/u/v/{email}/{otp}",method = RequestMethod.GET)
    public ResponseEntity<String> verifyUserEmail(@PathVariable @NotBlank String email,@PathVariable @NotBlank int otp){
        log.error("email : {}  , otp : {} ",email,otp);
        return ResponseEntity.ok().body(userService.verifyEmail(email,otp));
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseEntity< List<UserDtoWithoutToken>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @RequestMapping(value = "/user/reset-pass/{email}",method = RequestMethod.POST)
    public ResponseEntity<String> updateUserPass(@RequestParam  String password,@PathVariable @NotBlank String email){
        return ResponseEntity.ok().body(userService.updateUserPassByEmail(password,email));
    }

    @RequestMapping(value = "/users/role",method = RequestMethod.POST)
    public ResponseEntity<Users> saveRoleToUser(@RequestParam String userName,@RequestParam String userRole){
        userService.saveRoleToUser(userName,userRole);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/user/update-name/{email}",method = RequestMethod.PUT)
    public ResponseEntity<UserDtoWithoutToken> updateUserName(@RequestParam  String fullName,@PathVariable @NotBlank String email){
        return ResponseEntity.ok().body(userService.updateUserNameByEmail(fullName,email));
    }


    @RequestMapping(value = "/user/cred/d",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCredentialUser(@RequestParam @NotBlank String email){
        return ResponseEntity.ok().body(userService.deleteCredentialUser(email));
    }


    @RequestMapping(value = "/user/d/{email}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable @NotBlank String email){
        return ResponseEntity.ok().body(userService.deleteUserWithExtensions(email));
    }

    @RequestMapping(value = "/user/image",method = RequestMethod.POST)
    public ResponseEntity<String> uploadUserImage(@RequestParam MultipartFile image, @RequestParam long id){
        return ResponseEntity.ok().body(userService.uploadProfileImg(image,id));
    }




}
