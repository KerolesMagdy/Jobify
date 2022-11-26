package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "answerForm")
    @JsonManagedReference(value = "QuestionForm-question")
    private Set<AnswerQuestion> answerQuestions;
    @ManyToOne
    @JoinColumn(name = "job_apply_id")
    @JsonBackReference(value = "jobApply-answerform")
    private JobApply jobApply;
}
