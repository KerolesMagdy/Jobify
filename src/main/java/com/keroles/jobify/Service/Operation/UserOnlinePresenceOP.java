package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.UserOnlinePresenceDto;
import com.keroles.jobify.Model.Entity.UserOnlinePresence;

public interface UserOnlinePresenceOP {

    UserOnlinePresenceDto getByEmail(char[] email);
    UserOnlinePresenceDto save(char[] email);
    String updateEmail(char[] oldEmail,char[] newEmail);
    String delete(char[] email);

}
