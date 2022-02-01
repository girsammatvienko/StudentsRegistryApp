package com.example.studentsregistryapp.controller;

import com.example.studentsregistryapp.entity.Specialization;
import com.example.studentsregistryapp.exception.StudentsRegistryAppException;
import com.example.studentsregistryapp.service.FacultyService;
import com.example.studentsregistryapp.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/specializations")
public class SpecializationController {

    private SpecializationService specializationService;

    private FacultyService facultyService;

    @Autowired
    public SpecializationController(SpecializationService specializationService, FacultyService facultyService) {
        this.specializationService = specializationService;
        this.facultyService = facultyService;
    }

    @GetMapping
    public String getSpecializations(Model model) {
        var specializations = specializationService.getAllSpecializations();
        model.addAttribute("allSpeclizations", specializations);
        return "specialization-list";
    }

    @GetMapping("/addSpecialization")
    public String getAddSpecializationPage(Model model) {
        var specialization = new Specialization();
        model.addAttribute("specialization", specialization);
        var faculties = facultyService.getAllFaculties();
        model.addAttribute("allFaculties", faculties);
        return "specializations/add-specialization";
    }

    @PostMapping("/addSpecialization")
    public String addSpecialization(@ModelAttribute Specialization specialization, @ModelAttribute("facultyId") Long facultyId) throws StudentsRegistryAppException {
        var faculty = facultyService.findById(facultyId);
        specialization.setFaculty(faculty);
        specializationService.saveSpecialization(specialization);
        return "redirect:/students";
    }

}
