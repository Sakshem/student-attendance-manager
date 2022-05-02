package com.sakshem.studentmanager.service;

import com.sakshem.studentmanager.entity.StudentAttendance;
import com.sakshem.studentmanager.entity.StudentAttendanceKey;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface StudentAttendanceService {
    List<StudentAttendance> findAll();
    List<StudentAttendance> findAll(Optional<Date> date, Optional<Integer> studentClass, Optional<Character> section);
    // List<StudentAttendance> findAll(Optional<String> fullName, Optional<Date> date, Optional<Integer> studentClass, Optional<Character> section);
    StudentAttendance findById(String id);
    void save(StudentAttendance studentAttendance);
    void deleteById(String id);
    void markAttendance(String theId, Date date, boolean present);
    void addStudentRecordWithPreviousDates(StudentAttendance studentAttendance);
}