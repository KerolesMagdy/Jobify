package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {

    Users findByEmail(String email);
    @Modifying
    @Transactional
    @Query("update Users u set u.password=:password where u.email=:email")
    int updateUserPasswordByEmail(String password, String email);

    @Transactional
    @Modifying
    @Query("update Users u set u.enabled=1 where u.email=:email")
    int enableUser(String email);

    @Transactional
    @Modifying
    @Query("update Users u set u.enabled=0 where u.email=:email")
    int disableUser(String email);
    @Transactional
    int removeByEmail(String Email);
}
