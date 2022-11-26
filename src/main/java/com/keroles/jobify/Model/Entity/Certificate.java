package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "The given certificate name must not be null ")
    private String name;
    @JsonFormat(pattern="yyyy-MM")
    @NotNull(message = "The given date awarded must not be null ")
    private Date dateAwarded;
    private String organizationName;
    @Max(value = 100, message = "score must be from 0 to 100")
    @Min(value = 0, message = "score must be from 0 to 100")
    @NotNull(message = "The given score must not be null ")
    private int score;
    @Nullable
    private String certificateLink;
    private String additionalInfo;
    @ManyToOne
    @JoinColumn(name = "user_education_id")
    @JsonBackReference(value = "usereducation-certificate")
    @NotNull(message = "The given user education must not be null ")
    private UserEducation userEducation;
}
