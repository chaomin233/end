package com.abcd.student.domain.dto;

import com.abcd.student.domain.eo.Student;
import java.util.Collection;
import java.util.List;

/**
* @desc student
* @author 
*/
public class StudentDto extends Student{

public StudentDto(){};

public StudentDto(Student eo) {
    this.setId(eo.getId());
    this.setStudentId(eo.getStudentId());
    this.setStudentName(eo.getStudentName());
    this.setStudentSex(eo.getStudentSex());
    this.setStudentNumber(eo.getStudentNumber());
}
}

