package com.example.studentsregistryapp.controller;

import com.example.studentsregistryapp.entity.Specialization;
import com.example.studentsregistryapp.entity.Student;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.service.SpecializationService;
import com.example.studentsregistryapp.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
public class StudentsController {

    private StudentsService studentsService;

    private SpecializationService specializationService;

    @Autowired
    public StudentsController(StudentsService studentsService, SpecializationService specializationService) {
        this.studentsService = studentsService;
        this.specializationService = specializationService;
    }

    @GetMapping
    public String getStudentsListPage(Model model) {
        var students = studentsService.getAllStudents();
        model.addAttribute("students", students);
        return "students/students-list";
    }

    @GetMapping("/addStudent")
    private String getAddStudentPage(Model model) {
        var student = new Student();
        model.addAttribute("student", student);
        var specializations = specializationService.getAllSpecializations();
        model.addAttribute("allSpecializations", specializations);
        return "students/add-student";
    }

    @PostMapping("/addStudent")
    private String addStudent(Model model, @ModelAttribute("student") Student student, @ModelAttribute("specializationId") Long specializationId) throws StudentsRegistryAppException {
        var specialization = specializationService.findById(specializationId);
        student.setSpecialization(specialization);
        studentsService.saveStudent(student);
        return "redirect:/students";
    }


}
