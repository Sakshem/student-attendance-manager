package com.sakshem.studentmanager.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(StudentAttendanceKey.class) // new! for two different primary keys
@Table(name="student_attendance")
public class StudentAttendance {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="full_name")
    private String fullName;

    @Id
    @Column(name="date")
    private Date date;

    @Column(name="class")
    private int studentClass;

    @Column(name="section")
    private char section;

    @Column(name="present")
    private boolean present;

    public StudentAttendance() {}

    public StudentAttendance(String id, String fullName, Date date, int studentClass, char section, boolean present) {
        this.id = id;
        this.fullName = fullName;
        this.date = date;
        this.studentClass = studentClass;
        this.section = section;
        this.present = present;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(int studentClass) {
        this.studentClass = studentClass;
    }

    public char getSection() {
        return section;
    }

    public void setSection(char section) {
        this.section = section;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", date=" + date +
                ", studentClass=" + studentClass +
                ", section=" + section +
                ", present=" + present +
                '}';
    }
}
