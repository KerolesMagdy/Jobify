package com.keroles.jobify.Model.Mapper;


import com.keroles.jobify.Model.DTO.CertificateDto;
import com.keroles.jobify.Model.Entity.Certificate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CertificateMapper {
    CertificateDto mapToDto(Certificate certificate);
    Certificate mapToCertificate(CertificateDto certificateDto);
    List<CertificateDto> mapToDto(List<Certificate> certificates);

}
