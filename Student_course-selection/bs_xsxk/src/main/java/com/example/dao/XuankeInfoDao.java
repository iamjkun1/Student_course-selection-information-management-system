package com.example.dao;

import com.example.entity.XuankeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

////数据库操作层
@Repository
public interface XuankeInfoDao extends Mapper<XuankeInfo> {
    //判断学生有没有选过本课程
    @Select("select * from xuanke_info where name = #{name} and teacherId = #{teacherId} and studentId = #{studentId} limit 1")
    XuankeInfo find(@Param("name") String name, @Param("teacherId") Long teacherId, @Param("studentId") Long studentId);

    //查询到个人信息，保护隐私如：教师登录只看到教师个人信息，学生亦是如此
    List<XuankeInfo> findByCondition(@Param("teacherId") Long teacherId, @Param("studentId") Long studentId);


    //模糊查询
    @Select("select * from xuanke_info where name like concat('%', #{search}, '%')")
    List<XuankeInfo> findByLikeName(@Param("search") String search);
}
