package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCoursesDto {

    private Long id;
    private String name;
    private String organizationName;
    @JsonFormat(pattern = "yyyy-MM")
    private Date date;
    private String additionalInfo;
}
