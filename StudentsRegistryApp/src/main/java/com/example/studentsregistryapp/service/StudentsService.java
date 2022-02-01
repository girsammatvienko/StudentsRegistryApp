package com.example.studentsregistryapp.service;

import com.example.studentsregistryapp.entity.Student;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {

    private StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public void saveStudent(Student student) {
        studentsRepository.save(student);
    }

    public Student findById(Long id) throws StudentsRegistryAppException {
        var student = studentsRepository.findById(id);

        if(student.isEmpty())
            throw new StudentsRegistryAppException("Student not found");

        return student.get();
    }

    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

}
