package com.keroles.jobify.Model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExperienceDto {
    private Long id;
    private char[] email;
    private int workExperienceYears;
    private List<PreviousExperienceDto> previousExperienceList;
}
