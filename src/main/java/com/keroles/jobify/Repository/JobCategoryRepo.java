package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepo extends JpaRepository<JobCategory,Long> {
}
