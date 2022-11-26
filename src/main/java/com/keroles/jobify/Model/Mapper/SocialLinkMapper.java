package com.keroles.jobify.Model.Mapper;

import com.keroles.jobify.Model.DTO.SocialLinkDto;
import com.keroles.jobify.Model.Entity.SocialLink;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SocialLinkMapper {
    SocialLinkDto mapToDto(SocialLink socialLink);
    List<SocialLinkDto> mapToDto(List<SocialLink> socialLinkList);
}
