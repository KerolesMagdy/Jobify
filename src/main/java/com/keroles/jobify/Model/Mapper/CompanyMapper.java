package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.CompanyDto;
import com.keroles.jobify.Model.Entity.Company;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(uses = {JobOfferMapper.class})
public interface CompanyMapper {
    CompanyDto mapToCompanyDto(Company company);
    List<CompanyDto> mapToCompanyDto(List<Company> companyList);
}
