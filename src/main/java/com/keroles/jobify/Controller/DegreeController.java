package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.DegreeDto;
import com.keroles.jobify.Model.Entity.Degree;
import com.keroles.jobify.Service.Implementation.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DegreeController {

    @Autowired
    private DegreeService degreeService;
    @RequestMapping(value = "/degree/{id}",method = RequestMethod.GET)
    ResponseEntity<DegreeDto> getDegree(@PathVariable long id){
        return ResponseEntity.ok().body(degreeService.getDegree(id));
    }
    @RequestMapping(value = "/degree",method = RequestMethod.POST)
    ResponseEntity<DegreeDto> saveDegree(@RequestBody @Valid Degree degree){
        return ResponseEntity.ok().body(degreeService.saveDegree(degree));
    }
    @RequestMapping(value = "/degree",method = RequestMethod.PUT)
    ResponseEntity<DegreeDto> updateDegree(@RequestBody @Valid Degree degree){
        return ResponseEntity.ok().body(degreeService.updateDegree(degree));
    }
    @RequestMapping(value = "/degree/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteDegree(@PathVariable long id){
        return ResponseEntity.ok().body(degreeService.deleteDegree(id));
    }
}
