package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keroles.jobify.Model.Entity.SocialLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOnlinePresenceDto {
    private Long id;
    private String email;
    private List<SocialLinkDto> socialLinks;

}
