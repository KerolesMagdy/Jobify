package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.ExperienceLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceLanguageRepo extends JpaRepository<ExperienceLanguage,Long> {
}
