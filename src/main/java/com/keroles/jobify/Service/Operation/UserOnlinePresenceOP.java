package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.UserOnlinePresenceDto;
import com.keroles.jobify.Model.Entity.UserOnlinePresence;

public interface UserOnlinePresenceOP {

    UserOnlinePresenceDto getByEmail(String email);
    UserOnlinePresenceDto save(String email);
    String updateEmail(String oldEmail,String newEmail);
    String delete(String email);

}
