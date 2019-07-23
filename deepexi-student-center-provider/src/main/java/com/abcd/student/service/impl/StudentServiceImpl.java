package com.abcd.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.abcd.student.domain.eo.Student;
import com.abcd.student.service.StudentService;
import com.abcd.student.mapper.StudentMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageBean<Student> findPage(Student eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Student> pages =  studentMapper.findList(eo);
        return new PageBean<Student>(pages);
    }

    @Override
    public List<Student> findAll(Student eo) {
        List<Student> list = studentMapper.findList(eo);
        return list;
    }
    @Override
    public Student detail(String pk) {
        Student eo = studentMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(String id,Student eo) {
        Student old = studentMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = studentMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(Student eo) {
        int result = studentMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(String pk) {
        int result = studentMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(String...pks) {
        int result = studentMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}