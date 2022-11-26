package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.TrainingCoursesDto;
import com.keroles.jobify.Model.Entity.TrainingCourses;

public interface TrainingCoursesServiceOp {
    TrainingCoursesDto getTrainingCourses(long id);
    TrainingCoursesDto saveTrainingCourses(TrainingCourses trainingCourses);
    TrainingCoursesDto updateTrainingCourses(TrainingCourses trainingCourses);
    String deleteTrainingCourses(long id);
}
