package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.UserOnlinePresenceDto;
import com.keroles.jobify.Model.Entity.UserOnlinePresence;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = SocialLinkMapper.class)
public interface UserOnlinePresenceMapper {
    UserOnlinePresenceDto mapDto(UserOnlinePresence userOnlinePresence);
    List<UserOnlinePresenceDto> mapDto(List<UserOnlinePresence> userOnlinePresenceList);

}
