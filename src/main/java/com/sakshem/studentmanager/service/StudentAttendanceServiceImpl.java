package com.sakshem.studentmanager.service;

import com.sakshem.studentmanager.entity.DistinctStudentAttendanceResponse;
import com.sakshem.studentmanager.entity.StudentAttendance;
import com.sakshem.studentmanager.repository.StudentAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService{

    private final StudentAttendanceRepository studentAttendanceRepository;

    @Autowired
    public StudentAttendanceServiceImpl(StudentAttendanceRepository studentAttendanceRepository) {
        this.studentAttendanceRepository = studentAttendanceRepository;
    }

    @Override
    public List<StudentAttendance> findAll() {
        return studentAttendanceRepository.findAllByOrderByStudentClassDesc();
    }

    @Override
    public List<StudentAttendance> findAll(Optional<Date> date, Optional<Integer> studentClass, Optional<Character> section) {
        try {
            List<StudentAttendance> studentAttendances;
            if (date.isPresent() && studentClass.isPresent() && section.isPresent()) {
                studentAttendances = studentAttendanceRepository.findAll(date, studentClass, section);
                if (studentAttendances.isEmpty()) {
                     List<DistinctStudentAttendanceResponse> distinctStudents = distinctStudentAttendanceResponses();
                    // List<Object> distinctStudents = studentAttendanceRepository.findAllDistinctById();
                    Date currentDate = date.get();
                    // stuff to add here to create new records for a given date.
                    for (DistinctStudentAttendanceResponse distinctStudent : distinctStudents) {
                        studentAttendanceRepository.createStudentAttendance(distinctStudent.getId(), distinctStudent.getFullName(),
                                currentDate, distinctStudent.getStudentClass(), distinctStudent.getSection(), false);
                    }
                }
                return studentAttendanceRepository.findAll(date, studentClass, section);
            } else {
                return studentAttendanceRepository.findAllByOrderByStudentClassDesc();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    /* @Override
    public List<StudentAttendance> findAll(Optional<String> fullName, Optional<Date> date, Optional<Integer> studentClass, Optional<Character> section) {
        if (fullName.isPresent() && date.isPresent() && studentClass.isPresent() && section.isPresent()) {
            return studentAttendanceRepository.findAll(fullName, date, studentClass, section);
        }
        else {
            return studentAttendanceRepository.findAllByOrderByStudentClassDesc();
        }
    } */
    @Override
    public StudentAttendance findById(String id) {
        Optional<StudentAttendance> result = studentAttendanceRepository.findById(id);
        StudentAttendance theStudent = null;
        if (result.isPresent()) {
            theStudent = result.get();
        }
        else {
            throw new RuntimeException("Did not find student id - " + id);
        }
        return theStudent;
    }

    @Override
    public void save(StudentAttendance studentAttendance) {
        studentAttendanceRepository.save(studentAttendance);
    }

    @Override
    public void deleteById(String id) {
        studentAttendanceRepository.deleteById(id);
    }

    @Override
    public void markAttendance(String id, Date date, boolean present) {
        studentAttendanceRepository.setAttendance(id, date, present);
        // studentAttendanceRepository
    }

    public List<DistinctStudentAttendanceResponse> distinctStudentAttendanceResponses() {
        List<String> distinctStudents = studentAttendanceRepository.findAllDistinctById();
        List<DistinctStudentAttendanceResponse> myList = new ArrayList<>();
        try {
            for (String s : distinctStudents) {
                String[] response = s.split(",");
                DistinctStudentAttendanceResponse temp = new DistinctStudentAttendanceResponse(response[0], response[1], Integer.parseInt(response[2]), response[3].charAt(0));
                myList.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myList;
    }

    @Override
    public void addStudentRecordWithPreviousDates(StudentAttendance studentAttendance) {
        List<String> distinctDates = studentAttendanceRepository.findAllByDistinctDate();
        try {
            for (String s : distinctDates) {
                Date date = Date.valueOf(s);
                studentAttendance.setDate(date);
                save(studentAttendance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
