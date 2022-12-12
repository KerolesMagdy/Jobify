package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.Entity.UserOtp;

public interface UserOtpServiceOp {

    UserOtp getUserOtp(char[] email);
    UserOtp getCredentialUserOtp(char[] email);
    String sendOtpToVerifyEmail(char[] mailTo, char[]subject, char[]path);
    String refreshOtpToEmail(char[] mailTo);
}
