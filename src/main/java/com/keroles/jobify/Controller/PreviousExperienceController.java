package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.Custom.SavePrevExperienceForm;
import com.keroles.jobify.Model.DTO.PreviousExperienceDto;
import com.keroles.jobify.Model.Entity.PreviousExperience;
import com.keroles.jobify.Service.Implementation.PreviousExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class PreviousExperienceController {

    @Autowired
    private PreviousExperienceService previousExperienceService;

    @RequestMapping(value = "/prev-exp/{id}",method = RequestMethod.GET)
    ResponseEntity<PreviousExperienceDto> getPreviousExperience(@PathVariable long id){
        return ResponseEntity.ok().body(previousExperienceService.getByPreviousExperienceId(id));
    }

    @RequestMapping(value = "/prev-exp/u/exp/{id}",method = RequestMethod.GET)
    ResponseEntity<List<PreviousExperienceDto>> getPreviousExperienceList(@PathVariable long id){
        return ResponseEntity.ok().body(previousExperienceService.getByUserExperienceId(id));
    }

    @RequestMapping(value = "/prev-exp",method = RequestMethod.POST)
    ResponseEntity<PreviousExperienceDto> savePreviousExperience(@RequestBody SavePrevExperienceForm savePrevExperienceForm){
        return ResponseEntity.ok().body(previousExperienceService.save(savePrevExperienceForm));
    }

    @RequestMapping(value = "/prev-exp",method = RequestMethod.PUT)
    ResponseEntity<PreviousExperienceDto> updatePreviousExperience(@RequestBody PreviousExperience previousExperience){
        return ResponseEntity.ok().body(previousExperienceService.update(previousExperience));
    }

    @RequestMapping(value = "/prev-exp/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deletePreviousExperience(@PathVariable long id){
        return ResponseEntity.ok().body(previousExperienceService.deleteByPreviousExperienceId(id));
    }

    @RequestMapping(value = "prev-exp/u/exp/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deletePrevsExpByUserExpId(@PathVariable long id){
        return ResponseEntity.ok().body(previousExperienceService.deleteByUserExperienceId(id));
    }

}
