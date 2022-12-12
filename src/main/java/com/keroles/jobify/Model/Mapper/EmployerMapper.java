package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.EmployerDto;
import com.keroles.jobify.Model.Entity.Employer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CompanyMapper.class})

public interface EmployerMapper {
    EmployerDto mapToEmployerDto(Employer employer);
    List<EmployerDto> mapToEmployerDto(List<Employer> employer);
}
