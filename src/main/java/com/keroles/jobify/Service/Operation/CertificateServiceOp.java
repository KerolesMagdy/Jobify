package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.CertificateDto;
import com.keroles.jobify.Model.Entity.Certificate;

public interface CertificateServiceOp {
    CertificateDto getCertificate(long id);
    CertificateDto saveCertificate(Certificate certificate);
    CertificateDto updateCertificate(Certificate certificate);
    String deleteCertificate(long id);
}
