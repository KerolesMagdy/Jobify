package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @NotNull(message = "The question for this answer must not be null")
    private Question question;
    @NotNull(message = "The answer for the question must not be null")
    private String answer;
    @ManyToOne
    @JoinColumn(name = "answer_form_id")
    @JsonBackReference(value = "answerForm-answerQuestion")
    private AnswerForm answerForm;
}
