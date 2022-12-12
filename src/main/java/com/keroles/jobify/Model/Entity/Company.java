package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
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
    @NotBlank(message = "Company name must not be null")
    private String name;
    @OneToOne
    @JoinColumn(name = "industry_id")
    @NotNull(message = "Company industry must not be null")
    private JobCategory industry;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    @NotNull(message = "Company Address must not be null")
    private Address address;
    @NotNull(message = "Company maximum employees must not be null")
    private int empCountFrom;
    @NotNull(message = "Company minimum employees must not be null")
    private int empCountTo;
    @NotNull(message = "Company description must not be null")
    private String description;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "logo_id")
    @Nullable
    private Media logo;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "background_id")
    @Nullable
    private Media background;
    @OneToMany(mappedBy = "company",cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "company-joboffer")
    private List<JobOffer> jobOffers;
}
