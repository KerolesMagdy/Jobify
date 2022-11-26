package com.keroles.jobify.Model.DTO;

import com.keroles.jobify.Model.Entity.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceLanguageDto {
    private Long id;
    private Language language;
    private int readingRate;
    private int writeRate;
    private int speakRate;
    private int listenRate;
    private String additionalInfo;
}
