package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.UserOtp;

public interface UserOtpServiceOp {

    int generateOtp(String email);
    UserOtp getUserOtp(String email);
    UserOtp getCredentialUserOtp(String email);
    String sendOtpToUserByEmail(String mailTo);
}
