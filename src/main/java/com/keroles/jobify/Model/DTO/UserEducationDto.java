package com.keroles.jobify.Model.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keroles.jobify.Model.Entity.Certificate;
import com.keroles.jobify.Model.Entity.Degree;
import com.keroles.jobify.Model.Entity.DegreeLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEducationDto {
    private Long id;
    private char[] email;
    private DegreeLevel currentDegreeLevel;
    private List<DegreeDto> degrees;
    private List<CertificateDto> certificates;
    private List<TrainingCoursesDto> trainingCourses;
}
