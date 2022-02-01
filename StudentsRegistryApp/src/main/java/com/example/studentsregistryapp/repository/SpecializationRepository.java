package com.example.studentsregistryapp.repository;

import com.example.studentsregistryapp.entity.Specialization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepository extends CrudRepository<Specialization, Long> {
    List<Specialization> findAll();
}
