package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepo extends JpaRepository<Degree,Long> {
}
