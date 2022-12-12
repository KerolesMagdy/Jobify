package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.JobOfferSimpleDto;
import com.keroles.jobify.Model.Entity.JobOffer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface JobOfferMapper {
    JobOfferSimpleDto mapToJobOfferSimpleDto(JobOffer jobOffer);
    List<JobOfferSimpleDto> mapToJobOfferSimpleDto(List<JobOffer> jobOfferList);

}
