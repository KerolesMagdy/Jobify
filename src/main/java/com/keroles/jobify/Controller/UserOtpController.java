package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.Entity.UserOtp;
import com.keroles.jobify.Service.Implementation.UserOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
public class UserOtpController {
    @Autowired
    private UserOtpService userOtpService;

    @RequestMapping(value = "/otp",method = RequestMethod.POST)
    public ResponseEntity<UserOtp> getUserOtp(@RequestParam @NotBlank char[] email){
        return ResponseEntity.ok().body(userOtpService.getCredentialUserOtp(email));
    }

    @RequestMapping(value = "/otp/reset",method = RequestMethod.POST)
    public ResponseEntity<String> updateOtp(@RequestParam @NotBlank char[] email){
        return ResponseEntity.ok().body(userOtpService.refreshOtpToEmail(email));
    }

}
