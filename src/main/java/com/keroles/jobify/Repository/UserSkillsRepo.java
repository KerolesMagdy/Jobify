package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.UserSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserSkillsRepo extends JpaRepository<UserSkills,Long> {
    Optional<UserSkills> findByEmail(char[] email);
    @Transactional
    @Modifying
    @Query("update UserSkills u set u.email=:new_email where u.email=:old_email")
    int updateEmail(char[] old_email,char[] new_email);

}
