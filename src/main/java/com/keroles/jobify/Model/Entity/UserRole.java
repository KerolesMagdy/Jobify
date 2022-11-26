package com.keroles.jobify.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole {

    @Id
    @NotNull(message = "you must add an id")
    private Long id;
    @Column(unique = true)
    private String roleName;

}
