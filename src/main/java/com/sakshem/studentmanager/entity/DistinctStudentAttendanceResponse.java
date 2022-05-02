package com.sakshem.studentmanager.entity;

import javax.persistence.*;


@Entity
@Table(name = "student_attendance")
public class DistinctStudentAttendanceResponse {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "class")
    private int studentClass;

    @Column(name = "section")
    private char section;


    public DistinctStudentAttendanceResponse() {
    }

    public DistinctStudentAttendanceResponse(String id, String fullName, int studentClass, char section) {
        this.id = id;
        this.fullName = fullName;
        this.studentClass = studentClass;
        this.section = section;
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

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", studentClass=" + studentClass +
                ", section=" + section +
                '}';
    }
}

