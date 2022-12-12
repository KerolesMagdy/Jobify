package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class JobOffer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Offer title must not be null")
    private String offerTitle;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "job_details_id")
    private JobDetails jobDetails;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id")
    private Address address;
    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date created;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private Date lastModified;
    @Value("${validate.value.boolean.job_offer.blocked}")
    private boolean expired;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "question_form_id")
    private QuestionForm questionForm;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference(value = "company-joboffer")
    private Company company;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "jobOffer",cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "joboffer-jobApply")
    private List<JobApply> jobApplies;

}
