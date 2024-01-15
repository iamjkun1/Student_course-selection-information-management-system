package com.example.dao;

import com.example.entity.AdminInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

////数据库操作层
@Repository
public interface AdminInfoDao extends Mapper <AdminInfo>{
    @Select("select * from admin_info where name=#{name} and password=#{password}")
    AdminInfo findByNameAndPass(@Param("name") String name, @Param("password") String password);


    @Select("select * from admin_info where name=#{name}")
    AdminInfo findByName(@Param("name") String name);

    @Select("select * from admin_info where name like concat('%', #{name}, '%')")
    List<AdminInfo> findByNamePage(@Param("name")String name);
}
