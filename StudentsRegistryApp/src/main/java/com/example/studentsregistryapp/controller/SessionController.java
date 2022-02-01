package com.example.studentsregistryapp.controller;

import com.example.studentsregistryapp.entity.SessionResult;
import com.example.studentsregistryapp.entity.SessionType;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.service.FacultyService;
import com.example.studentsregistryapp.service.SessionResultService;
import com.example.studentsregistryapp.service.SpecializationService;
import com.example.studentsregistryapp.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sessions")
public class SessionController {

    private SessionResultService sessionResultService;

    private StudentsService studentsService;

    private FacultyService facultyService;

    private SpecializationService specializationService;

    @Autowired
    public SessionController(SessionResultService sessionResultService, StudentsService studentsController,
                             FacultyService facultyService, SpecializationService specializationService) {
        this.sessionResultService = sessionResultService;
        this.studentsService = studentsController;
        this.facultyService = facultyService;
        this.specializationService = specializationService;
    }

    @GetMapping
    public String getAllSessionsResults(Model model) {
        var sessionsResults = sessionResultService.getAllSessionsResults();
        model.addAttribute("allSessionsResults", sessionsResults);
        var faculties = facultyService.getAllFaculties();
        model.addAttribute("faculties", faculties);
        var specialization = specializationService.getAllSpecializations();
        model.addAttribute("allSpecializations", specialization);
        return "sessions/sessions-results-list";
    }

    @GetMapping("/addSessionResult")
    public String getAddSessionResultPage(Model model) {
        var students = studentsService.getAllStudents();
        model.addAttribute("allStudents", students);
        return "sessions/add-session-result";
    }

    @PostMapping("/addSessionResult")
    public String addSessionResult(@ModelAttribute SessionResult sessionResult, @ModelAttribute("studentId") Long studentId, @ModelAttribute("sessionType") String sesionType) throws StudentsRegistryAppException {
        var student = studentsService.findById(studentId);
        var sessionType = SessionType.valueOf(sesionType);
        sessionResult.setSessionType(sessionType);
        sessionResult.setStudent(student);
        sessionResultService.saveSessionResult(sessionResult);
        return "redirect:/sessions";
    }

    @GetMapping("/getDStudents")
    public String getDStudents(Model model) {
        var dStudents = sessionResultService.getDStudentsList();
        model.addAttribute("dStudents", dStudents);
        return "students/d-students-list";
    }

    @GetMapping("/getExcellentStudents")
    public String getExcellentStudents(Model model) {
        var excellentStudents = sessionResultService.getExcellentStudentsList();
        model.addAttribute("excellentStudents", excellentStudents);
        return "students/excellent-students-list";
    }

    @GetMapping("/getResultsByType")
    public String getResultsBySessionType(@ModelAttribute("sessionType") String sessionType, Model model) {
        System.out.println(sessionType);
        var sessions = sessionResultService.findAllByType(SessionType.valueOf(sessionType));
        model.addAttribute("sessions", sessions);
        return "sessions/results-by-type";
    }

    @GetMapping("/getResultsByFaculty")
    public String getResultsByFaculty(@ModelAttribute("facultyId") Long facultyId, Model model) {
        var sessions = sessionResultService.findAllByFaculty(facultyId);
        model.addAttribute("sessions", sessions);
        return "sessions/results-by-type";
    }

    @GetMapping("/getResultsBySpecialization")
    public String getResultsBySpecialization(@ModelAttribute("specializationId") Long specializationId, Model model) {
        var sessions = sessionResultService.findAllBySpecialization(specializationId);
        model.addAttribute("sessions", sessions);
        return "sessions/results-by-type";
    }

}
