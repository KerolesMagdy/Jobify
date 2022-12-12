package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Media;
import com.keroles.jobify.Model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {

    Users findByEmail(char[] email);
    @Modifying
    @Transactional
    @Query("update Users u set u.password=:password where u.email=:email")
    int updateUserPasswordByEmail(char[] password, char[] email);

    @Modifying
    @Transactional
    @Query("update Users u set u.media=:media where u.id=:id")
    int updateUserImageById(Media media, long id);

    @Modifying
    @Transactional
    @Query("update Users u set u.userCv=:media where u.id=:id")
    int updateUserCvById(Media media, long id);
    @Transactional
    @Modifying
    @Query("update Users u set u.enabled=:enable where u.email=:email")
    int enableUser(char[] email,boolean enable);

    @Transactional
    int removeByEmail(char[] Email);
}
