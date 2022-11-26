package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDetailsRepo extends JpaRepository<JobDetails,Long> {
}
