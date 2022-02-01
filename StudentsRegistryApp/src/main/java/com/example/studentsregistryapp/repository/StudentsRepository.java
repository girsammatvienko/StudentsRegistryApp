package com.example.studentsregistryapp.repository;

import com.example.studentsregistryapp.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {
    List<Student> findAll();
}
