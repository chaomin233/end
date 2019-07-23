package com.abcd.student.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.abcd.student.service.StudentService;
import com.abcd.student.domain.eo.Student;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/学生表", description = "$desc")
@RestController
@RequestMapping("/api/v1/students")
public class  StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(Student eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(studentService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(Student eo) {
        return new Payload(studentService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) String pk) {
        return new Payload(studentService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改Student")
    public Payload update(@PathVariable(value = "id", required = true) String pk, @RequestBody Student eo) {
     eo.setId(pk);
     return new Payload(studentService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建Student", notes = "创建Student")
    public Payload create(@RequestBody Student eo) {
        return new Payload(studentService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除Student", notes = "根据id删除Student")
    public Payload delete(@PathVariable(value = "id", required = true) String pk) {
        return new Payload(studentService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除Student", notes = "根据id批量删除Student")
    public Payload delete(@RequestParam(required = true) String[] ids) {
        return new Payload(studentService.delete(ids));
    }

}