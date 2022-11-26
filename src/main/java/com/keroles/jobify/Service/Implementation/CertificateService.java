package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.CertificateDto;
import com.keroles.jobify.Model.Entity.Certificate;
import com.keroles.jobify.Model.Mapper.CertificateMapper;
import com.keroles.jobify.Repository.CertificateRepo;
import com.keroles.jobify.Service.Operation.CertificateServiceOp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CertificateService implements CertificateServiceOp {
    private final CertificateRepo certificateRepo;
    private final CertificateMapper certificateMapper;
    private final Environment environment;

    public CertificateService(CertificateRepo certificateRepo, CertificateMapper certificateMapper, Environment environment) {
        this.certificateRepo = certificateRepo;
        this.certificateMapper = certificateMapper;
        this.environment = environment;
    }

    @Override
    public CertificateDto getCertificate(long id) {
            Optional<Certificate> certificate = certificateRepo.findById(id);
            if (!certificate.isPresent())
                throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.certificate.not_found"));
            return certificateMapper.mapToDto(certificate.get());
    }

    @Override
    public CertificateDto saveCertificate( Certificate certificate) {
        return certificateMapper.mapToDto(certificateRepo.save(certificate));
    }

    @Transactional
    @Override
    public CertificateDto updateCertificate( Certificate certificate) {
        if (certificate.getId()==null)
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.object.id.not_found"));
        Optional<Certificate> retrievedCertificate=certificateRepo.findById(certificate.getId());
        if (!retrievedCertificate.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.certificate.not_found"));
        return certificateMapper.mapToDto(certificateRepo.save(certificate));
    }

    @Transactional
    @Override
    public String deleteCertificate(long id) {
            Optional<Certificate> retrievedCertificate = certificateRepo.findById(id);
            if (!retrievedCertificate.isPresent())
                throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.certificate.not_found"));
            certificateRepo.delete(retrievedCertificate.get());
            return "degree deleted successfully";
    }
}
