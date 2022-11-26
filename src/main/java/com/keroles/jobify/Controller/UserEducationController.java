package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.UserEducationDto;
import com.keroles.jobify.Model.Entity.UserEducation;
import com.keroles.jobify.Repository.UserEducationRepo;
import com.keroles.jobify.Service.Implementation.UserEducationService;
import com.keroles.jobify.Service.Implementation.UserExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserEducationController {

    @Autowired
    private UserEducationService userEducationService;

    @RequestMapping(value = "/education/{email}",method = RequestMethod.GET)
    ResponseEntity<UserEducationDto> getUserEducation(@PathVariable String email){
        return ResponseEntity.ok().body(userEducationService.getByEmail(email));
    }
    @RequestMapping(value = "/education/update/e",method = RequestMethod.PUT)
    ResponseEntity<String> updateEducationEmail(@RequestParam String old_email,@RequestParam String new_email){
        return ResponseEntity.ok().body(userEducationService.updateEmail(old_email,new_email));
    }
    @RequestMapping(value = "/education",method = RequestMethod.PUT)
    ResponseEntity<String> updateEducationCurrentLeve(@RequestParam String email,@RequestParam long level_id){
        return ResponseEntity.ok().body(userEducationService.updateCurrentDegreeLevel(level_id,email));
    }
    @RequestMapping(value = "/education/{email}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteEducation(@PathVariable String email){
        return ResponseEntity.ok().body(userEducationService.deleteUserEducation(email));
    }


}
