package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOnlinePresence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private char[] email;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="userOnlinePresence",cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "useronlinepresence-sociallink")
    private List<SocialLink> socialLinks;

}
