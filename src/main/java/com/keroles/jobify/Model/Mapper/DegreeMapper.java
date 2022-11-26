package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.DegreeDto;
import com.keroles.jobify.Model.Entity.Degree;
import org.mapstruct.Mapper;
import java.util.List;


@Mapper
public interface DegreeMapper {
    DegreeDto mapToDto(Degree degree);
    List<DegreeDto> mapToDto(List<Degree> degrees);
}
