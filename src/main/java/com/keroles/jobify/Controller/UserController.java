package com.keroles.jobify.Controller;

import com.keroles.jobify.Media.MediaFile;
import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Custom.UserForm;
import com.keroles.jobify.Model.Entity.Media;
import com.keroles.jobify.Model.Entity.Users;
import com.keroles.jobify.Service.Implementation.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
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
    @RequestMapping(value = "/token/user/refresh",method = RequestMethod.POST)
    public void saveRoleToUser(){}
    @RequestMapping(value = "/u/v",method = RequestMethod.GET)
    public ResponseEntity<String> verifyUserEmail(@RequestParam @NotBlank char[] email,@RequestParam @NotBlank int otp){
        log.error("email : {}  , otp : {} ",email,otp);
        return ResponseEntity.ok().body(userService.verifyEmail(email,otp));
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseEntity< List<UserDtoWithoutToken>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @RequestMapping(value = "/user/reset-pass/{email}",method = RequestMethod.POST)
    public ResponseEntity<String> updateUserPass(@RequestParam  char[] password,@PathVariable @NotBlank char[] email){
        return ResponseEntity.ok().body(userService.updateUserPassByEmail(password,email));
    }

    @RequestMapping(value = "/users/role",method = RequestMethod.POST)
    public ResponseEntity<Users> saveRoleToUser(@RequestParam char[] userName,@RequestParam char[] userRole){
        userService.saveRoleToUser(userName,userRole);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/user/update-name/{email}",method = RequestMethod.PUT)
    public ResponseEntity<UserDtoWithoutToken> updateUserName(@RequestParam  char[] fullName,@PathVariable @NotBlank char[] email){
        return ResponseEntity.ok().body(userService.updateUserNameByEmail(fullName,email));
    }


    @RequestMapping(value = "/user/cred/d",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCredentialUser(@RequestParam @NotBlank char[] email){
        return ResponseEntity.ok().body(userService.deleteCredentialUser(email));
    }


    @RequestMapping(value = "/user/d/{email}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable @NotBlank char[] email){
        return ResponseEntity.ok().body(userService.deleteUserWithExtensions(email));
    }
    @RequestMapping(value = "/user/{userId}/image",method = RequestMethod.POST)
    public ResponseEntity<Media> uploadUserImage(@RequestParam MultipartFile image, @PathVariable long userId){
        Media media=userService.uploadProfileImg(image,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(media);
    }

    @RequestMapping(value = "/user/image",method = RequestMethod.GET)
    public ResponseEntity<?> loadUserImage(@RequestParam("uId") long userId, @RequestParam("img") String imageName){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(userService.loadProfileImg(userId,imageName));
    }
    @RequestMapping(value = "/user/{userId}/cv",method = RequestMethod.POST)
    public ResponseEntity<Media> uploadUserCv(@RequestParam MultipartFile cv, @PathVariable long userId){
        Media media=userService.uploadUserCv(cv,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(media);
    }
    @RequestMapping(value = "/user/cv/",method = RequestMethod.GET)
    public ResponseEntity<?> loadUserCv(@RequestParam("uId") long userId, @RequestParam("file") String fileName,@RequestParam String type){
        MediaFile mediaFile =userService.downloadUserCv(userId,fileName,type);
        return ResponseEntity
                .ok()
                .headers(mediaFile.getHeaders())
                .body(mediaFile.getFileBytes());
    }



}
