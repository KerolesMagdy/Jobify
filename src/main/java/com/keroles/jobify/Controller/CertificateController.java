package com.keroles.jobify.Controller;

import com.keroles.jobify.Model.DTO.CertificateDto;
import com.keroles.jobify.Model.DTO.DegreeDto;
import com.keroles.jobify.Model.Entity.Certificate;
import com.keroles.jobify.Model.Entity.Degree;
import com.keroles.jobify.Service.Implementation.CertificateService;
import com.keroles.jobify.Service.Implementation.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CertificateController {

    @Autowired
    private CertificateService certificateService;
    @RequestMapping(value = "/certificate/{id}",method = RequestMethod.GET)
    ResponseEntity<CertificateDto> getCertificateDto(@PathVariable long id){
        return ResponseEntity.ok().body(certificateService.getCertificate(id));
    }
    @RequestMapping(value = "/certificate",method = RequestMethod.POST)
    ResponseEntity<CertificateDto> saveCertificate(@RequestBody @Valid Certificate certificate){
        return ResponseEntity.ok().body(certificateService.saveCertificate(certificate));
    }
    @RequestMapping(value = "/certificate",method = RequestMethod.PUT)
    ResponseEntity<CertificateDto> updateCertificate(@RequestBody @Valid Certificate certificate){
        return ResponseEntity.ok().body(certificateService.updateCertificate(certificate));
    }
    @RequestMapping(value = "/certificate/{id}",method = RequestMethod.DELETE)
    ResponseEntity<String> deleteCertificate(@PathVariable long id){
        return ResponseEntity.ok().body(certificateService.deleteCertificate(id));
    }
}
