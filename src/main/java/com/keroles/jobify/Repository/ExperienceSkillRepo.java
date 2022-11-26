package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.ExperienceSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceSkillRepo extends JpaRepository<ExperienceSkill,Long> {
}
