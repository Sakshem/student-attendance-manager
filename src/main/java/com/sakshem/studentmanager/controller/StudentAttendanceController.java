package com.sakshem.studentmanager.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sakshem.studentmanager.entity.StudentAttendance;
import com.sakshem.studentmanager.entity.StudentAttendanceKey;
import com.sakshem.studentmanager.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students-attendance")
public class StudentAttendanceController {
    private StudentAttendanceService studentAttendanceService;

    public StudentAttendanceController() {}

    @Autowired
    public StudentAttendanceController(StudentAttendanceService studentAttendanceService) {
        this.studentAttendanceService = studentAttendanceService;
    }

    @GetMapping("/list")
    public String listRecords(Model theModel, @RequestParam("date") Optional<Date> date, @RequestParam("studentClass") Optional<Integer> studentClass, @RequestParam("section") Optional<Character> section) {
        List<StudentAttendance> studentsAttendance;
        if (studentClass.isPresent() && section.isPresent() && date.isPresent()) {
            studentsAttendance = studentAttendanceService.findAll(date, studentClass, section);
            theModel.addAttribute("date", date);
            theModel.addAttribute("studentClass", studentClass);
            theModel.addAttribute("section", section);
        } else {
            studentsAttendance = studentAttendanceService.findAll();
        }
        theModel.addAttribute("studentsAttendance", studentsAttendance);

        /* Original Code:
            List<StudentAttendance> studentAttendance;
            studentAttendance = studentAttendanceService.findAll(date);
            theModel.addAttribute("date", date);
            theModel.addAttribute("studentAttendance", studentAttendance);
         */
        return "students/list-students";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        StudentAttendance studentAttendance = new StudentAttendance();
        theModel.addAttribute("studentAttendance", studentAttendance);
        return "students/student-form";
    }

    @GetMapping("/showFormForUpdate")
    // original string id instead of StudentAttendanceKey theId;
    public String showFormForUpdate(@RequestParam("id") String theId, Model theModel) {
        StudentAttendance studentAttendance = studentAttendanceService.findById(theId);
        theModel.addAttribute("studentAttendance", studentAttendance);
        return "students/student-form";
    }

    @PostMapping("/save")
    public String saveStudentAttendance(@ModelAttribute("studentAttendance") StudentAttendance theStudent) {
        studentAttendanceService.addStudentRecordWithPreviousDates(theStudent);
        return "redirect:/students-attendance/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") String theId) {
        studentAttendanceService.deleteById(theId);
        return "redirect:/students-attendance/list";
    }

    @GetMapping("/mark-attendance")
    public String markAttendance(@RequestParam("id") String theId, @RequestParam("date") Date date, @RequestParam("present") boolean present,
                                 @RequestParam("studentClass") String studentClass, @RequestParam("section") String section) {
        studentAttendanceService.markAttendance(theId, date, present);
        return "redirect:/students-attendance/list?date=" + date.toString() + "&studentClass=" + studentClass + "&section=" + section;
        // return "redirect:/students-attendance/list";
    }
}