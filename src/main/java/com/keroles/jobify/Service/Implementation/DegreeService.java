package com.keroles.jobify.Service.Implementation;

import com.keroles.jobify.Exception.Exceptions.Global.GlobalIdNotFoundException;
import com.keroles.jobify.Exception.Exceptions.Global.GlobalObjectNotFoundException;
import com.keroles.jobify.Model.DTO.DegreeDto;
import com.keroles.jobify.Model.Entity.Degree;
import com.keroles.jobify.Model.Mapper.DegreeMapper;
import com.keroles.jobify.Repository.DegreeRepo;
import com.keroles.jobify.Service.Operation.DegreeServiceOp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class DegreeService implements DegreeServiceOp {
    private final DegreeRepo degreeRepo;
    private final DegreeMapper degreeMapper;
    private final Environment environment;

    public DegreeService(DegreeRepo degreeRepo, DegreeMapper degreeMapper, Environment environment) {
        this.degreeRepo = degreeRepo;
        this.degreeMapper = degreeMapper;
        this.environment = environment;
    }

    @Override
    public DegreeDto getDegree(long id) {
            Optional<Degree> degree = degreeRepo.findById(id);
            if (!degree.isPresent())
                throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.degree.not_found"));
            return degreeMapper.mapToDto(degree.get());
    }

    @Override
    public DegreeDto saveDegree( Degree degree) {
        return degreeMapper.mapToDto(degreeRepo.save(degree));
    }

    @Transactional
    @Override
    public DegreeDto updateDegree( Degree degree) {
        if (degree.getId()==null)
            throw new GlobalIdNotFoundException(environment.getProperty("validate.message.object.id.not_found"));
        Optional<Degree> retrievedDegree=degreeRepo.findById(degree.getId());
        if (!retrievedDegree.isPresent())
            throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.degree.not_found"));
        return degreeMapper.mapToDto(degreeRepo.save(degree));
    }

    @Transactional
    @Override
    public String deleteDegree(long id) {
            Optional<Degree> retrievedDegree = degreeRepo.findById(id);
            if (!retrievedDegree.isPresent())
                throw new GlobalObjectNotFoundException(environment.getProperty("validate.message.user.degree.not_found"));
            degreeRepo.delete(retrievedDegree.get());
            return "degree deleted successfully";
    }
}
