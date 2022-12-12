package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.UserOnlinePresence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserOnlinePresenceRepo extends JpaRepository<UserOnlinePresence,Long> {
    Optional<UserOnlinePresence> findByEmail(char[] email);

    @Transactional
    @Modifying
    @Query("update UserOnlinePresence o set o.email=:new_email where o.email=:old_email")
    int updateEmail(char[] old_email, char[] new_email);

    @Transactional
    int deleteByEmail(char[] email);
}
