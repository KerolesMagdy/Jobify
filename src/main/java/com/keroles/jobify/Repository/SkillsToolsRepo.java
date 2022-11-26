package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.SkillsTools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsToolsRepo extends JpaRepository<SkillsTools,Long> {
}
