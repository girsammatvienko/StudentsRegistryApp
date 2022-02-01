package com.example.studentsregistryapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "session_results")
public class SessionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "session_type")
    private SessionType sessionType;

    @ManyToOne
    private Student student;

    @Column(name = "math_mark")
    private Integer mathMark;

    @Column(name = "algorithms_theory_mark")
    private Integer algorithmsTheoryMark;

    @Column(name = "physics_mark")
    private Integer physicsMark;

    @Column(name = "operation_systems_mark")
    private Integer operationSystemsMark;

}
