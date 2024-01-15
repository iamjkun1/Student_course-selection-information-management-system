package com.example.dao;

import com.example.entity.TeacherInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

////数据库操作层
@Repository
public interface TeacherInfoDao extends Mapper<TeacherInfo> {


    @Select("select * from teacher_info where name=#{name}")
    TeacherInfo findByName(@Param("name") String name);

    //精确查询
    @Select("select * from teacher_info where name=#{name} and password=#{password}")
    TeacherInfo findByNameAndPass(@Param("name") String name, @Param("password") String password);

    //模糊查询
    @Select("select * from teacher_info where name like concat('%', #{search}, '%')")
    List<TeacherInfo> findByLikeName(@Param("search") String search);
}
