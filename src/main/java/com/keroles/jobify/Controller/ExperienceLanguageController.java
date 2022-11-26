package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.ExperienceLanguageDto;
import com.keroles.jobify.Model.Entity.ExperienceLanguage;
import com.keroles.jobify.Service.Implementation.ExperienceLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ExperienceLanguageController {


    @Autowired
    private ExperienceLanguageService experienceLanguageService;

    @RequestMapping(value = "experience-Language",method = RequestMethod.POST)
    ResponseEntity<ExperienceLanguageDto> saveExperienceLanguage(@RequestBody @Valid ExperienceLanguage experienceLanguage){
        return ResponseEntity.ok().body(experienceLanguageService.save(experienceLanguage));
    }
    @RequestMapping(value = "experience-Language",method = RequestMethod.PUT)
    ResponseEntity<ExperienceLanguageDto> updateExperienceLanguage(@RequestBody @Valid ExperienceLanguage experienceLanguage){
        return ResponseEntity.ok().body(experienceLanguageService.update(experienceLanguage));
    }
    @RequestMapping(value = "experience-Language/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteExperienceLanguage(@PathVariable long id){
        return ResponseEntity.ok().body(experienceLanguageService.delete(id));
    }
}
