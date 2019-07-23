package com.abcd.student.service;

import com.abcd.student.domain.eo.Student;
import com.abcd.student.domain.dto.StudentDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * student
 */
public interface StudentService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<Student> findPage(Student eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<Student> findAll(Student eo);

    /**
      获取详情
    * @return
    */
    Student detail(String pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(String id,Student eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(Student eo);

    /**
     * 单个删除
    * @return
    */
    Boolean delete(String pk);

    /**
     批量删除
    * @return
    */
    Boolean delete(String...pk);
}