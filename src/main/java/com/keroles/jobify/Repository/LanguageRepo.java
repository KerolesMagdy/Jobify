package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepo extends JpaRepository<Language,Long> {
}
