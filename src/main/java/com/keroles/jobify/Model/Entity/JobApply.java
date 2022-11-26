package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "jobApply")
    @JsonManagedReference(value = "jobApply-answerform")
    private Set<AnswerForm> answerForms;
    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    @JsonBackReference(value = "joboffer-jobApply")
    private JobOffer jobOffer;
}
