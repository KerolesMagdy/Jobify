package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.UserExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserExperienceRepo extends JpaRepository<UserExperience,Long> {

    Optional<UserExperience> findByEmail(String email);
    int removeByEmail(String email);
}
