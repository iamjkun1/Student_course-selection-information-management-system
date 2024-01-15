package com.example.dao;

import com.example.entity.StudentInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


////数据库操作层
@Repository
public interface StudentInfoDao extends Mapper<StudentInfo> {

    //精确查询
    @Select("select * from student_info where name=#{name} and password=#{password}")
    StudentInfo findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    @Select("select * from student_info where name=#{name}")
    StudentInfo findByName(@Param("name") String name);

    ///Sql关联查询，查找学生表学院ID与学院表学院名称相关联
    @Select("SELECT a.*, b.name as xueyuanName from student_info as a left JOIN xueyuan_info as b on a.xueyuanId = b.id")
    List<StudentInfo> findAllJoinXueyuan();


    //模糊查询
    @Select("select * from student_info where name like concat('%', #{search}, '%')")
    List<StudentInfo> findByLikeName(@Param("search") String search);


    /*//模糊查询
    @Select("select * from student_info where name like concat('%', #{search},'%')")
    List<StudentInfo> findBySearch(@Param("search") String search);*/
}
