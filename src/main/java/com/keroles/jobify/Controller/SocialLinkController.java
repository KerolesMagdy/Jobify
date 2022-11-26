package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.SocialLinkDto;
import com.keroles.jobify.Model.Entity.SocialLink;
import com.keroles.jobify.Service.Implementation.SocialLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SocialLinkController {

    @Autowired
    private SocialLinkService socialLinkService;
    @RequestMapping(value = "/social-link",method = RequestMethod.POST)
    ResponseEntity<SocialLinkDto> saveSocialLink(@RequestBody @Valid SocialLink socialLink){
        return ResponseEntity.ok().body(socialLinkService.save(socialLink));
    }
    @RequestMapping(value = "/social-link",method = RequestMethod.PUT)
    ResponseEntity<SocialLinkDto> updateSocialLink(@RequestBody @Valid SocialLink socialLink){
        return ResponseEntity.ok().body(socialLinkService.update(socialLink));
    }
    @RequestMapping(value = "/social-link/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteSocialLink(@PathVariable long id){
        return ResponseEntity.ok().body(socialLinkService.deleteById(id));
    }
}
