package com.example.dao;

import com.example.entity.XueyuanInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

////数据库操作层
@Repository
public interface XueyuanInfoDao extends Mapper<XueyuanInfo> {

    //精准查找重名
    @Select("select * from xueyuan_info where name = #{name}")
    XueyuanInfo findByName(@Param("name") String name);

    //模糊查询
    @Select("select * from xueyuan_info where name like concat ('%',#{search},'%')")
    List<XueyuanInfo> findByLikeName(@Param("search") String search);

    /*//模糊查询
    @Select("select * from xueyuan_info where name like concat ('%',#{search},'%')")
    List<XueyuanInfo> find(@Param("search") String search);*/
}
