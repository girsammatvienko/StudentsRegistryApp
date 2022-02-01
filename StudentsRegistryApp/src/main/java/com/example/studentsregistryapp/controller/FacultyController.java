package com.example.studentsregistryapp.controller;

import com.example.studentsregistryapp.entity.Faculty;
import com.example.studentsregistryapp.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    private FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/addFaculty")
    public String getAddFacultyPage(Model model) {
        var faculty = new Faculty();
        model.addAttribute("faculty", faculty);
        return "faculties/add-faculty";
    }

    @PostMapping("/addFaculty")
    public String addFaculty(@ModelAttribute Faculty faculty) {
        facultyService.addFaculty(faculty);
        return "redirect:/students";
    }
}
