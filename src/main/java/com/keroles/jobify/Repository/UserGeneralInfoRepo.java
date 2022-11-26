package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.UserGeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGeneralInfoRepo extends JpaRepository<UserGeneralInfo,Long> {

    Optional<UserGeneralInfo> findByEmail(String email);
    int removeByEmail(String Email);

}
