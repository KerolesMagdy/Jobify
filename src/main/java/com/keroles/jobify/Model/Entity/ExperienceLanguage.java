package com.keroles.jobify.Model.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "language_id")
    @NotNull(message = "language name must not be null")
    private Language language;
    @Max(value = 5,message = "reading rate must be from 0 to 5")
    @Min(value = 0,message = "reading rate must be from 0 to 5")
    @NotNull(message = "reading rate must not be null")
    private int readingRate;
    @Max(value = 5,message = "writing rate must be from 0 to 5")
    @Min(value = 0,message = "writing rate must be from 0 to 5")
    @NotNull(message = "writing rate must not be null")
    private int writeRate;
    @Max(value = 5,message = "speaking rate must be from 0 to 5")
    @Min(value = 0,message = "speaking rate must be from 0 to 5")
    @NotNull(message = "speaking rate must not be null")
    private int speakRate;
    @Max(value = 5,message = "listening rate must be from 0 to 5")
    @Min(value = 0,message = "listening rate must be from 0 to 5")
    @NotNull(message = "listening rate must not be null")
    private int listenRate;
    @Nullable
    private String additionalInfo;
    @ManyToOne
    @JoinColumn(name = "user_skills_id")
    @JsonBackReference(value = "userskills-experiencelanguage")
    @NotNull(message = "user in experienced language must not be null")
    private UserSkills userSkills;

}
