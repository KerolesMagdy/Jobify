package com.keroles.jobify.Model.Entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "country_id")
    @NotNull(message = "country must not be null")
    private Country country;
    @NotNull(message = "city must not be null")
    private String city;
    @Nullable
    private String area;
    private int postalCode;
    private boolean relocate;
}
