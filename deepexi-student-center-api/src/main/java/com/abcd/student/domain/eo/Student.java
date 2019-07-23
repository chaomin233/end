package com.abcd.student.domain.eo;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Collection;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;


/**
* @desc student
* @author 
*/
//@ApiModel(description = "学生表")
public class Student{

// @ApiModelProperty(value = "主键")
    @TableId
    @TableField(value = "`id`")
    private String id;
// @ApiModelProperty(value = "学号")
    @TableField(value = "`student_id`")
    private String studentId;
// @ApiModelProperty(value = "姓名")
    @TableField(value = "`student_name`")
    private String studentName;
// @ApiModelProperty(value = "性别")
    @TableField(value = "`student_sex`")
    private String studentSex;
// @ApiModelProperty(value = "电话号码")
    @TableField(value = "`student_number`")
    private Integer  studentNumber;

    public void setId(String id){
    this.id = id;
    }

    public String getId(){
    return this.id;
    }

    public void setStudentId(String studentId){
    this.studentId = studentId;
    }

    public String getStudentId(){
    return this.studentId;
    }

    public void setStudentName(String studentName){
    this.studentName = studentName;
    }

    public String getStudentName(){
    return this.studentName;
    }

    public void setStudentSex(String studentSex){
    this.studentSex = studentSex;
    }

    public String getStudentSex(){
    return this.studentSex;
    }

    public void setStudentNumber(Integer  studentNumber){
    this.studentNumber = studentNumber;
    }

    public Integer  getStudentNumber(){
    return this.studentNumber;
    }


}

