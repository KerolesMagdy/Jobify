package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.DegreeLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeLevelRepo extends JpaRepository<DegreeLevel,Long> {
}
