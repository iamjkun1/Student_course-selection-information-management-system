package com.example.dao;

import com.example.entity.ZhuanyeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

////专业数据库操作层
@Repository
public interface ZhuanyeInfoDao extends Mapper<ZhuanyeInfo> {

    //精确查询，防止重复
    @Select("select * from zhuanye_info where name = #{name}")
    ZhuanyeInfo findByName(@Param("name") String name);

    //模糊查询,包括关联查询到学院表的学院名称
    @Select("select a.*, b.name as xueyuanName from zhuanye_info as a left join xueyuan_info as b on a.xueyuanId = b.id where a.name like concat ('%',#{search},'%')")
    List<ZhuanyeInfo> findByLikeName(@Param("search") String search);

     /*//模糊查询,包括关联查询到学院表的学院名称
    @Select("select a.*, b.name as xueyuanName from zhuanye_info as a left join xueyuan_info as b on a.xueyuanId = b.id where a.name like concat ('%',#{search},'%')")
    List<ZhuanyeInfo> findBySearch(@Param("search") String search);*/
}
