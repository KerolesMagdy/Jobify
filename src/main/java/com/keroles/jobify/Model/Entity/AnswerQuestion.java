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
    @JoinColumn(name = "question_id")
    @NotNull
    private Question question;
    @Nullable
    private String answer;
    @ManyToOne
    @JoinColumn(name = "answer_form_id")
    @JsonBackReference(value = "AnswerForm-answerQuestion")
    private AnswerForm answerForm;
}
