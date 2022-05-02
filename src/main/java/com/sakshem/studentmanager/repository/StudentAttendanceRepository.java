package com.sakshem.studentmanager.repository;

import com.sakshem.studentmanager.entity.DistinctStudentAttendanceResponse;
import com.sakshem.studentmanager.entity.StudentAttendance;
import com.sakshem.studentmanager.entity.StudentAttendanceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, String> {
//    public List<StudentAttendance> findAllByOrderByStudentClassAndSectionAscAndDateDesc();
    public List<StudentAttendance> findAllByOrderByStudentClassDesc();

    @Query("SELECT s FROM StudentAttendance s WHERE s.date = :date AND s.studentClass = :studentClass AND s.section = :section")
    public List<StudentAttendance> findAll(@RequestParam("date") Optional<Date> date, @RequestParam("studentClass") Optional<Integer> studentClass, @RequestParam("section") Optional<Character> section);

/*    @Query("SELECT s FROM StudentAttendance s WHERE s.fullName LIKE %?1% AND s.date = :date AND s.studentClass = :studentClass AND s.section = :section")
    public List<StudentAttendance> findAll(@RequestParam("fullName") Optional<String> fullName, @RequestParam("date") Optional<Date> date, @RequestParam("studentClass") Optional<Integer> studentClass, @RequestParam("section") Optional<Character> section); */

    @Query("SELECT DISTINCT id, fullName, studentClass, section FROM StudentAttendance")
    public List<String> findAllDistinctById();

    @Query("SELECT DISTINCT date FROM StudentAttendance")
    public List<String> findAllByDistinctDate();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO student_attendance (id, full_name, date, class, section, present) VALUES(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    public void createStudentAttendance(String id, String fullName, Date date, int studentClass, char section, boolean present);

    @Modifying
    @Transactional
    @Query("UPDATE StudentAttendance s SET s.present = :presentOrAbsent WHERE s.id = :id AND s.date = :date")
    void setAttendance(@RequestParam("id") String id, @RequestParam("date") Date date, @RequestParam("present") boolean presentOrAbsent);
}