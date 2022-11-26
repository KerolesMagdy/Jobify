package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country,Long> {
    Country findByName(String country);
}
