package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.UserExperienceDto;
import com.keroles.jobify.Service.Implementation.UserExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserExperienceController {

    @Autowired
    private UserExperienceService userExperienceService;
    @RequestMapping(value = "/user-experience/{email}",method = RequestMethod.GET)
    public ResponseEntity<UserExperienceDto> getUserExperience(@PathVariable char[] email){
        return ResponseEntity.ok().body(userExperienceService.getByEmail(email));
    }
    @RequestMapping(value = "/user-experience",method = RequestMethod.PUT)
    public ResponseEntity<String> getUserExperience(@RequestParam char[] email,@RequestParam int years){
        return ResponseEntity.ok().body(userExperienceService.updateUserUserExperienceYears(email,years));
    }
    @RequestMapping(value = "/user-experience/{id}",method = RequestMethod.DELETE)
    public void getUserExperience(@PathVariable int id){
        userExperienceService.deleteById(id);
    }
}
