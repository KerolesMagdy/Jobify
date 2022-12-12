package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Employer;
import com.keroles.jobify.Model.Entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmployerRepo extends JpaRepository<Employer,Long> {

    Optional<Employer> findByEmail(char[] email);
    @Modifying
    @Transactional
    @Query("update Employer e set e.password=:newPassword where e.email=:email")
    int updateEmployerPassById(char[] email,char[] newPassword);
    @Modifying
    @Transactional
    @Query("update Employer e set e.profileImage=:profileImage where e.id=:employerId")
    int updateEmployerLogoById(long employerId, Media profileImage);
    @Transactional
    @Modifying
    @Query("update Employer e set e.enabled=:enable where e.email=:email")
    int changeEmployerState(char[] email,boolean enable);

    @Transactional
    int removeByEmail(char[] email);
}
