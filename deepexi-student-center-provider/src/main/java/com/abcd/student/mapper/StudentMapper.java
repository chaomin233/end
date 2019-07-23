package com.abcd.student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.abcd.student.domain.eo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  StudentMapper extends BaseMapper<Student> {

    List<Student> findList(@Param("eo")  Student eo);

    int deleteByIds(@Param("ids") List<String> ids);

}