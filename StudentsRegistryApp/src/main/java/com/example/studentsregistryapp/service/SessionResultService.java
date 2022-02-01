package com.example.studentsregistryapp.service;

import com.example.studentsregistryapp.entity.SessionResult;
import com.example.studentsregistryapp.entity.SessionType;
import com.example.studentsregistryapp.entity.Student;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.repository.SessionResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionResultService {

    private SessionResultRepository sessionResultRepository;

    @Autowired
    public SessionResultService(SessionResultRepository sessionResultRepository) {
        this.sessionResultRepository = sessionResultRepository;
    }

    public List<SessionResult> findAllByType(SessionType type) {
        return sessionResultRepository.findAllBySessionType(type);
    }

    public List<SessionResult> findAllByFaculty(Long facultyId) {
        var results = sessionResultRepository.findAll();
        return results.stream()
                .filter(result -> result.getStudent().getSpecialization().getFaculty().getId().equals(facultyId))
                .collect(Collectors.toList());
    }

    public List<SessionResult> findAllBySpecialization(Long specializationId) {
        var results = sessionResultRepository.findAll();
        return results.stream()
                .filter(result -> result.getStudent().getSpecialization().getId().equals(specializationId))
                .collect(Collectors.toList());
    }

    public List<Student> getDStudentsList() {
        var results = sessionResultRepository.findAll();
        return results.stream()
                .filter(result -> result.getMathMark() < 60)
                .filter(result -> result.getPhysicsMark() < 60)
                .filter(result -> result.getAlgorithmsTheoryMark() < 60)
                .filter(result -> result.getOperationSystemsMark() < 60)
                .map(SessionResult::getStudent)
                .collect(Collectors.toList());
    }

    public List<Student> getExcellentStudentsList() {
        var results = sessionResultRepository.findAll();
        return results.stream()
                .filter(result -> result.getMathMark() > 85)
                .filter(result -> result.getPhysicsMark() > 85)
                .filter(result -> result.getAlgorithmsTheoryMark() > 85)
                .filter(result -> result.getOperationSystemsMark() > 85)
                .map(SessionResult::getStudent)
                .collect(Collectors.toList());
    }

    public List<SessionResult> getAllSessionsResults() {
        return sessionResultRepository.findAll();
    }

    public void saveSessionResult(SessionResult sessionResult) throws StudentsRegistryAppException {
        var existingSessionResult = sessionResultRepository.findByStudentPhone(sessionResult.getStudent().getPhone());

        if(existingSessionResult != null && existingSessionResult.getSessionType().equals(sessionResult.getSessionType()))
            throw new StudentsRegistryAppException("");

        sessionResultRepository.save(sessionResult);
    }
}
