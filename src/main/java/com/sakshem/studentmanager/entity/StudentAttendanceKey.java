package com.sakshem.studentmanager.entity;

import java.io.Serializable;
import java.sql.Date;
// for two primary key's student id and date
public class StudentAttendanceKey implements Serializable {
    private String id;
    private Date date;
}