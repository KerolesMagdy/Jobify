package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.ExperienceLanguageDto;
import com.keroles.jobify.Model.DTO.ExperienceSkillDto;
import com.keroles.jobify.Model.Entity.ExperienceLanguage;
import com.keroles.jobify.Model.Entity.ExperienceSkill;
import com.keroles.jobify.Service.Implementation.ExperienceLanguageService;
import com.keroles.jobify.Service.Implementation.ExperienceSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ExperienceSkillController {


    @Autowired
    private ExperienceSkillService experienceSkillService;

    @RequestMapping(value = "experience-skill",method = RequestMethod.POST)
    ResponseEntity<ExperienceSkillDto> saveExperienceSkill(@RequestBody @Valid ExperienceSkill experienceSkill){
        return ResponseEntity.ok().body(experienceSkillService.save(experienceSkill));
    }
    @RequestMapping(value = "experience-skill",method = RequestMethod.PUT)
    ResponseEntity<ExperienceSkillDto> updateExperienceSkill(@RequestBody @Valid ExperienceSkill experienceSkill){
        return ResponseEntity.ok().body(experienceSkillService.update(experienceSkill));
    }
    @RequestMapping(value = "experience-skill/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteExperienceSkill(@PathVariable long id){
        return ResponseEntity.ok().body(experienceSkillService.delete(id));
    }
}
