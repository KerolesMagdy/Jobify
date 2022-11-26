package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.AnswerForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerFormRepo extends JpaRepository<AnswerForm,Long> {
}
