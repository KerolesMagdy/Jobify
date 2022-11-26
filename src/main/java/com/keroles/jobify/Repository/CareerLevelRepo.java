package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.CareerLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerLevelRepo extends JpaRepository<CareerLevel,Long> {
    CareerLevel findByName(String name);
}
