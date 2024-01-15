package com.example.dao;

import com.example.entity.ClassInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

////数据库操作层
@Repository
public interface ClassInfoDao extends Mapper<ClassInfo> {

    //关联选课表中显示老师姓名和学生姓名
    @Select("select a.* ,b.name as teacherName from class_info as a left join teacher_info as b on a.teacherId = b.id")
    List<ClassInfo> findAll();

    //取消选课，把课表老师相关联删除，人数-1
    @Select("select * from class_info where name = #{name} and teacherId = #{teacherId} limit 1")
    ClassInfo findByNameAndTeacher(@Param("name") String name, @Param("teacherId") Long teacherId);

    //搜索框模糊查询
    @Select("select a.* ,b.name as teacherName from class_info as a left join teacher_info as b on a.teacherId = b.id where a.name like concat('%', #{search}, '%')")
    List<ClassInfo> findByLikeName(@Param("search") String search);

   /* //搜索框模糊查询
    @Select("select a.* ,b.name as teacherName from class_info as a left join teacher_info as b on a.teacherId = b.id where a.name like concat('%', #{search}, '%')")
    List<ClassInfo> findSearch(@Param("search") String search);*/
}
