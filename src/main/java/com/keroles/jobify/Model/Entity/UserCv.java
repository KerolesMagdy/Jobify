package com.keroles.jobify.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserCv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    private String cvLink;
}
