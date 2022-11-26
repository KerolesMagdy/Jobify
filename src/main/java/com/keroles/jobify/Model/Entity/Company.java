package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "industry_id")
    private JobCategory industry;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private int empCountFrom;
    private int empCountTo;
    private String description;
    private String logo;
    private String background;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "company")
    @JsonManagedReference(value = "company-joboffer")
    private Set<JobOffer> jobOffers;

}
