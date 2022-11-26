package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.TrainingCoursesDto;
import com.keroles.jobify.Model.Entity.TrainingCourses;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TrainingCoursesMapper {
    TrainingCoursesDto mapToDto(TrainingCourses trainingCourses);
    List<TrainingCoursesDto> mapToDto(List<TrainingCourses> trainingCourses);

}
