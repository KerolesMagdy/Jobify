package com.keroles.jobify.Service.Operation;

import com.keroles.jobify.Model.DTO.DegreeDto;
import com.keroles.jobify.Model.Entity.Degree;

public interface DegreeServiceOp {
    DegreeDto getDegree(long id);
    DegreeDto saveDegree(Degree degree);
    DegreeDto updateDegree(Degree degree);
    String deleteDegree(long id);
}
