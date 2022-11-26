package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.CertificateDto;
import com.keroles.jobify.Model.DTO.TrainingCoursesDto;
import com.keroles.jobify.Model.Entity.Certificate;
import com.keroles.jobify.Model.Entity.TrainingCourses;
import com.keroles.jobify.Service.Implementation.CertificateService;
import com.keroles.jobify.Service.Implementation.TrainingCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TrainingCoursesController {

    @Autowired
    private TrainingCoursesService trainingCoursesService;
    @RequestMapping(value = "/training-courses/{id}",method = RequestMethod.GET)
    ResponseEntity<TrainingCoursesDto> getTrainingCourses(@PathVariable long id){
        return ResponseEntity.ok().body(trainingCoursesService.getTrainingCourses(id));
    }
    @RequestMapping(value = "/training-courses",method = RequestMethod.POST)
    ResponseEntity<TrainingCoursesDto> saveTrainingCourses(@RequestBody @Valid TrainingCourses trainingCourses){
        return ResponseEntity.ok().body(trainingCoursesService.saveTrainingCourses(trainingCourses));
    }
    @RequestMapping(value = "/training-courses",method = RequestMethod.PUT)
    ResponseEntity<TrainingCoursesDto> updateTrainingCourses(@RequestBody @Valid TrainingCourses trainingCourses){
        return ResponseEntity.ok().body(trainingCoursesService.updateTrainingCourses(trainingCourses));
    }
    @RequestMapping(value = "/training-courses/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteTrainingCourses(@PathVariable long id){
        return ResponseEntity.ok().body(trainingCoursesService.deleteTrainingCourses(id));
    }
}
