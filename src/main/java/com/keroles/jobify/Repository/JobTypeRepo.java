package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTypeRepo extends JpaRepository<JobType,Long> {
}
