package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.Entity.UserCareerInterests;
import com.keroles.jobify.Service.Implementation.UserCareerInterestsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserCareerInterestsController {

    @Autowired
    private UserCareerInterestsServiceImpl userCareerInterestsService;

    @RequestMapping(value = "/career-interests/{email}",method = RequestMethod.GET)
    public ResponseEntity<UserCareerInterests> getCareerInterestsByEmail(@PathVariable char[] email){
        return ResponseEntity.ok().body(userCareerInterestsService.getCareerInterestsByEmail(email));
    }

    @RequestMapping(value = "/career-interests",method = RequestMethod.POST)
    public ResponseEntity<UserCareerInterests> saveCareerInterests(@RequestBody @Valid UserCareerInterests careerInterests){
        return ResponseEntity.ok().body(userCareerInterestsService.save(careerInterests));
    }

    @RequestMapping(value = "/career-interests",method = RequestMethod.PUT)
    public ResponseEntity<UserCareerInterests> updateCareerInterests(@RequestBody @Valid UserCareerInterests careerInterests){
        return ResponseEntity.ok().body(userCareerInterestsService.update(careerInterests));
    }
}
