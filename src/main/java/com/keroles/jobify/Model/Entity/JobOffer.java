package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer extends BaseAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String offerTitle;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference(value = "company-joboffer")
    private Company company;
    @OneToOne
    @JoinColumn(name = "job_details_id")
    private JobDetails jobDetails;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @Value("${validate.value.boolean.job_offer.blocked}")
    private boolean blocked;
    @OneToOne
    @JoinColumn(name = "question_form_id")
    private QuestionForm questionForm;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "jobOffer")
    @JsonManagedReference(value = "joboffer-jobApply")
    private Set <JobApply>jobApplies;

}
