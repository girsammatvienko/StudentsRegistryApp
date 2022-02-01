package com.example.studentsregistryapp.repository;

import com.example.studentsregistryapp.entity.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {
    List<Faculty> findAll();
}
