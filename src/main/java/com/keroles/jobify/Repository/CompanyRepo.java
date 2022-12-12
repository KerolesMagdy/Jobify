package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Company;
import com.keroles.jobify.Model.Entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {

    @Modifying
    @Transactional
    @Query("update Company c set c.logo=:logo where c.id=:companyId")
    int updateCompanyLogoById(long companyId, Media logo);
    @Modifying
    @Transactional
    @Query("update Company c set c.background=:background where c.id=:companyId")
    int updateCompanyBackgroundById(long companyId,Media background);


}
