package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.UserOnlinePresenceDto;
import com.keroles.jobify.Model.Entity.UserOnlinePresence;
import com.keroles.jobify.Service.Implementation.UserOnlinePresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserOnlinePresenceController {

    @Autowired
    private UserOnlinePresenceService userOnlinePresenceService;

    @RequestMapping(value = "/user/online-presence/{email}",method = RequestMethod.GET)
    ResponseEntity<UserOnlinePresenceDto> getUserOnlinePresence(@PathVariable String email){
        return ResponseEntity.ok().body(userOnlinePresenceService.getByEmail(email));
    }

    @RequestMapping(value = "/user/online-presence/{email}",method = RequestMethod.POST)
    ResponseEntity<UserOnlinePresenceDto> saveUserOnlinePresence(@PathVariable String email){
        return ResponseEntity.ok().body(userOnlinePresenceService.save(email));
    }

    @RequestMapping(value = "/user/online-presence",method = RequestMethod.PUT)
    ResponseEntity<String> updateUserOnlinePresence(@RequestParam String old_email,@RequestParam String new_email){
        return ResponseEntity.ok().body(userOnlinePresenceService.updateEmail(old_email,new_email));
    }

    @RequestMapping(value = "/user/online-presence/{email}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteUserOnlinePresence(@PathVariable String email){
        return ResponseEntity.ok().body(userOnlinePresenceService.delete(email));
    }
}
