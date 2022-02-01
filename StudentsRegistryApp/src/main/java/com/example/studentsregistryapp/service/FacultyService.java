package com.example.studentsregistryapp.service;

import com.example.studentsregistryapp.entity.Faculty;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty findById(Long id) throws StudentsRegistryAppException {
        var faculty = facultyRepository.findById(id);
        if(faculty.isEmpty())
            throw new StudentsRegistryAppException("Faculty not found");

        return faculty.get();
    }
}
