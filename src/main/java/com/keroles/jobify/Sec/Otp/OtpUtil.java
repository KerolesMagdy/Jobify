package com.keroles.jobify.Sec.Otp;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class OtpUtil {
    private final long OTP_VALIDITY=1800L;//7days
    private final int MINE=100000;
    private final int MAX=999999;
    public Otp generateRandomOtp(){
        return Otp.builder()
                .otp((int)((Math.random()*(MAX-MINE))+MINE))
                .expired(new Date(System.currentTimeMillis()+OTP_VALIDITY*1000))
                .build();
    }

    public Otp generateRandomOtp(int optLength){
        return Otp.builder()
                .otp(new Random().nextInt(optLength))
                .expired(new Date(System.currentTimeMillis()+OTP_VALIDITY*1000))
                .build();
    }

    public boolean isOtpExpired(Date expirationDate){
        return expirationDate.before(new Date());
    }

}
