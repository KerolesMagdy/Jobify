package com.keroles.jobify.Model.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class JobCategory {
    @Id
    @NotNull(message = "you must add an id")
    private Long id;
    private String name;
}
