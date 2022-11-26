package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.DegreeLevel;
import com.keroles.jobify.Model.Entity.UserEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserEducationRepo extends JpaRepository<UserEducation,Long> {

    Optional<UserEducation> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update UserEducation u set u.email=:new_email where u.email=:old_email")
    int updateEmail(String old_email,String new_email);

    @Transactional
    @Modifying
    @Query("update UserEducation u set u.currentDegreeLevel=:currentLevel where u.email=:email")
    int updateCurrentLevel(DegreeLevel currentLevel, String email);
}
