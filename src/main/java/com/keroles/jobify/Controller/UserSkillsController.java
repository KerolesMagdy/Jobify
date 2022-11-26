package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.UserEducationDto;
import com.keroles.jobify.Model.DTO.UserSkillsDto;
import com.keroles.jobify.Service.Implementation.UserEducationService;
import com.keroles.jobify.Service.Implementation.UserSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserSkillsController {

    @Autowired
    private UserSkillService userSkillService;

    @RequestMapping(value = "/user-skills/{email}",method = RequestMethod.GET)
    ResponseEntity<UserSkillsDto> get(@PathVariable String email){
        return ResponseEntity.ok().body(userSkillService.getByEmail(email));
    }

    @RequestMapping(value = "/user-skills/{email}",method = RequestMethod.POST)
    ResponseEntity<UserSkillsDto> save(@PathVariable String email){
        return ResponseEntity.ok().body(userSkillService.save(email));
    }
    @RequestMapping(value = "/user-skills/update/e",method = RequestMethod.PUT)
    ResponseEntity<String> updateUserSkillsEmail(@RequestParam String old_email,@RequestParam String new_email){
        return ResponseEntity.ok().body(userSkillService.updateEmail(old_email,new_email));
    }
    @RequestMapping(value = "/user-skills/{email}",method = RequestMethod.DELETE)
    ResponseEntity<String> delete(@PathVariable String email){
        return ResponseEntity.ok().body(userSkillService.delete(email));
    }


}
