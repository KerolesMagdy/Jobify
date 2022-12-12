package com.keroles.jobify.Model.DTO;

import com.keroles.jobify.Model.Entity.Address;
import com.keroles.jobify.Model.Entity.JobCategory;
import com.keroles.jobify.Model.Entity.Media;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private JobCategory industry;
    private Address address;
    private int empCountFrom;
    private int empCountTo;
    private String description;
    private Media logo;
    private Media background;
    private List<JobOfferSimpleDto> jobOffers;

}
