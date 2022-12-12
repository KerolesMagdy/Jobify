package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.UserOnlinePresenceDto;
import com.keroles.jobify.Model.Entity.UserOnlinePresence;
import com.keroles.jobify.Model.Mapper.UserOnlinePresenceMapper;
import com.keroles.jobify.Repository.UserOnlinePresenceRepo;
import com.keroles.jobify.Service.Operation.UserOnlinePresenceOP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserOnlinePresenceService implements UserOnlinePresenceOP {

    @Autowired
    private UserOnlinePresenceRepo userOnlinePresenceRepo;
    @Autowired
    private UserOnlinePresenceMapper userOnlinePresenceMapper;
    @Autowired
    private Environment environment;

    @Override
    public UserOnlinePresenceDto getByEmail(char[] email) {
        Optional<UserOnlinePresence> userOnlinePresence=userOnlinePresenceRepo.findByEmail(email);
        log.error("11111111111111111111111111111111");
        if (!userOnlinePresence.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.social_link.not_found"));
        log.error("222222222222222222222222222");
        return userOnlinePresenceMapper.mapDto(userOnlinePresence.get());
    }

    @Override
    public UserOnlinePresenceDto save(char[] email) {
        Optional<UserOnlinePresence> userOnlinePresence=userOnlinePresenceRepo.findByEmail(email);
        if (userOnlinePresence.isPresent())
            throw new GlobalObjectFoundException(environment.getProperty("validate.message.user.social_link.found"));
        return userOnlinePresenceMapper.mapDto(userOnlinePresenceRepo.save(new UserOnlinePresence(null,email,null)));
    }

    @Override
    public String updateEmail(char[] oldEmail, char[] newEmail) {
        Optional<UserOnlinePresence> userOnlinePresence=userOnlinePresenceRepo.findByEmail(oldEmail);
        if (!userOnlinePresence.isPresent())
            throw new GlobalObjectFoundException(environment.getProperty("validate.message.user.social_link.not_found"));
        if (userOnlinePresenceRepo.updateEmail(oldEmail,newEmail)>0)
            return "email updated successfully";
        return "Something went wrong";
    }

    @Override
    public String delete(char[] email) {
        Optional<UserOnlinePresence> userOnlinePresence=userOnlinePresenceRepo.findByEmail(email);
        if (!userOnlinePresence.isPresent())
            throw new GlobalObjectFoundException(environment.getProperty("validate.message.user.social_link.not_found"));
        if (userOnlinePresenceRepo.deleteByEmail(email)>0)
            return "email deleted successfully";
        return "Something went wrong";    }
}
