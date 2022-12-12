package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOtpRepo extends JpaRepository<UserOtp,Long> {

    UserOtp findByEmail(char[] email);
}
