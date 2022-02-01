package com.example.studentsregistryapp.service;

import com.example.studentsregistryapp.entity.Specialization;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    private SpecializationRepository specializationRepository;

    public SpecializationService(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    public void saveSpecialization(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    public Specialization findById(Long id) throws StudentsRegistryAppException {
       var specialization = specializationRepository.findById(id);
       if(specialization.isEmpty())
           throw new StudentsRegistryAppException("Specialization not found");

       return specialization.get();
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }
}
