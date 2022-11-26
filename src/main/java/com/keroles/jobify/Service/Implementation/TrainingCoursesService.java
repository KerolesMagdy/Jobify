package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.TrainingCoursesDto;
import com.keroles.jobify.Model.Entity.TrainingCourses;
import com.keroles.jobify.Model.Mapper.TrainingCoursesMapper;
import com.keroles.jobify.Repository.TrainingCoursesRepo;
import com.keroles.jobify.Service.Operation.TrainingCoursesServiceOp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class TrainingCoursesService implements TrainingCoursesServiceOp {
    private final TrainingCoursesRepo trainingCoursesRepo;
    private final TrainingCoursesMapper trainingCoursesMapper;
    private final Environment environment;

    public TrainingCoursesService(TrainingCoursesRepo trainingCoursesRepo, TrainingCoursesMapper trainingCoursesMapper, Environment environment) {
        this.trainingCoursesRepo = trainingCoursesRepo;
        this.trainingCoursesMapper = trainingCoursesMapper;
        this.environment = environment;
    }

    @Override
    public TrainingCoursesDto getTrainingCourses(long id) {
            Optional<TrainingCourses> retrievedTrainingCourses = trainingCoursesRepo.findById(id);
            if (!retrievedTrainingCourses.isPresent())
                throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.training_courses.not_found"));
            return trainingCoursesMapper.mapToDto(retrievedTrainingCourses.get());
    }

    @Override
    public TrainingCoursesDto saveTrainingCourses( TrainingCourses trainingCourses) {
        return trainingCoursesMapper.mapToDto(trainingCoursesRepo.save(trainingCourses));
    }

    @Transactional
    @Override
    public TrainingCoursesDto updateTrainingCourses( TrainingCourses trainingCourses) {
        if (trainingCourses.getId()==null)
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.object.id.not_found"));
        Optional<TrainingCourses> retrievedCertificate=trainingCoursesRepo.findById(trainingCourses.getId());
        if (!retrievedCertificate.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.training_courses.not_found"));
        return trainingCoursesMapper.mapToDto(trainingCoursesRepo.save(trainingCourses));
    }

    @Transactional
    @Override
    public String deleteTrainingCourses(long id) {
            Optional<TrainingCourses> retrievedTrainingCourses = trainingCoursesRepo.findById(id);
            if (!retrievedTrainingCourses.isPresent())
                throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.training_courses.not_found"));
        trainingCoursesRepo.delete(retrievedTrainingCourses.get());
            return "degree deleted successfully";
    }
}
