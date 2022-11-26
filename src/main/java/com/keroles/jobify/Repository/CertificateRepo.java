package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate,Long> {
}
