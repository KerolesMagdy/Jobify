package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
}
