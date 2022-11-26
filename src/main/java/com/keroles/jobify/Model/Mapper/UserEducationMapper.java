package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.UserEducationDto;
import com.keroles.jobify.Model.Entity.UserEducation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {DegreeMapper.class,CertificateMapper.class,TrainingCoursesMapper.class})
public interface UserEducationMapper {
    UserEducationDto mapToDto(UserEducation userEducation);
    List<UserEducationDto> mapToDto(List <UserEducation> userEducations);
}
