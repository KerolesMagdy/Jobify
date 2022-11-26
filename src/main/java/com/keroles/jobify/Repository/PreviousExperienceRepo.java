package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.PreviousExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface PreviousExperienceRepo extends JpaRepository<PreviousExperience,Long> {
    List<PreviousExperience> findByUserExperience_Id(long userExperienceId);
    @Transactional
    void deleteByUserExperience_Id(long userExperienceId);
}
