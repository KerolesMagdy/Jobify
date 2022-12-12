package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.CertificateDto;
import com.keroles.jobify.Model.Entity.Certificate;
import com.keroles.jobify.Repository.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;

public interface CertificateServiceOp {
    CertificateDto getCertificate(long id);
    CertificateDto saveCertificate(Certificate certificate);
    CertificateDto updateCertificate(Certificate certificate);
    String deleteCertificate(long id);
}
