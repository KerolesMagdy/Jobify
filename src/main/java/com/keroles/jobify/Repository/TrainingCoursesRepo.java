package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.TrainingCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingCoursesRepo extends JpaRepository<TrainingCourses,Long> {
}
