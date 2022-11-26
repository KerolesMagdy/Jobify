package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.UserDto;
import com.keroles.jobify.Model.DTO.UserDtoWithoutToken;
import com.keroles.jobify.Model.Custom.UserForm;
import com.keroles.jobify.Model.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper
public interface UserMapper {
    UserDto mapUserToDto(Users user);
    List<UserDto> mapUserToDto(List<Users> user);
    UserDtoWithoutToken mapUserToDtoWithoutToken(Users user);
    List<UserDtoWithoutToken> mapUserToDtoWithoutToken(List<Users> user);
    Users mapRegisterFormToUser(UserForm form) ;
    Users mapDtoToUser(UserForm form, @MappingTarget Users user) ;

}
