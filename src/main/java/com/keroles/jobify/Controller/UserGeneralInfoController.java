package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.Entity.UserGeneralInfo;
import com.keroles.jobify.Service.Implementation.UserGeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserGeneralInfoController {

    @Autowired
    private UserGeneralInfoService userGeneralInfoService;

    @RequestMapping(value = "/user/gen-details/{email}",method = RequestMethod.GET)
    public ResponseEntity<UserGeneralInfo> getUserGeneralInfo(@PathVariable String email){
        return ResponseEntity.ok().body(userGeneralInfoService.getInfoCredentialByEmail(email));
    }

    @RequestMapping(value = "/user/gen-details",method = RequestMethod.POST)
    public ResponseEntity<UserGeneralInfo> saveGeneralInfo(@RequestBody @Valid UserGeneralInfo userGeneralInfo){
        return ResponseEntity.ok().body(userGeneralInfoService.save(userGeneralInfo));
    }

    @RequestMapping(value = "/user/gen-details",method = RequestMethod.PUT)
    public ResponseEntity<UserGeneralInfo> updateGeneralInf(@RequestBody @Valid UserGeneralInfo userGeneralInfo){
        return ResponseEntity.ok().body(userGeneralInfoService.update(userGeneralInfo));
    }

    @RequestMapping(value = "/user/gen-details/{email}",method = RequestMethod.DELETE)
    public ResponseEntity<Integer> deleteUserGeneralInfo(@PathVariable String email){
        return ResponseEntity.ok().body(userGeneralInfoService.remove(email));
    }


}
