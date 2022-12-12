package com.keroles.jobify.Model.DTO;

import com.keroles.jobify.Model.Entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferSimpleDto {

    private Long id;
    private String offerTitle;
    private Address address;
    private boolean expired;
    private Date created;
    private Date lastModified;


}
