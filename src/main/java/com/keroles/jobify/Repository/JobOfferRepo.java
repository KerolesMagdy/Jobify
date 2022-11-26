package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepo extends JpaRepository<JobOffer,Long> {
}
