package com.example.studentsregistryapp.repository;

import com.example.studentsregistryapp.entity.SessionResult;
import com.example.studentsregistryapp.entity.SessionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionResultRepository extends CrudRepository<SessionResult, Long> {
    List<SessionResult> findAll();

    List<SessionResult> findAllBySessionType(SessionType sessionType);

    SessionResult findByStudentPhone(String phone);
}
