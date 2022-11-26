package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.UserCareerInterests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserCareerInterestsRepo extends JpaRepository<UserCareerInterests,Long> {
    UserCareerInterests findByEmail(String email);
    @Transactional
    int removeByEmail(String email);

}
