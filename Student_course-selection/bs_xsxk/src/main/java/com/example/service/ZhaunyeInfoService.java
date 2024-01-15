package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.dao.XueyuanInfoDao;
import com.example.dao.ZhuanyeInfoDao;
import com.example.entity.XueyuanInfo;
import com.example.entity.ZhuanyeInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/////写业务逻辑层
@Service
public class ZhaunyeInfoService {

    @Resource
    private ZhuanyeInfoDao zhuanyeInfoDao;
    @Resource
    private XueyuanInfoDao xueyuanInfoDao;

    public void add(ZhuanyeInfo zhuanyeInfo) {
        ZhuanyeInfo info = zhuanyeInfoDao.findByName(zhuanyeInfo.getName());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException("-1", "你输入的专业名称已存在");
        }
        zhuanyeInfoDao.insertSelective(zhuanyeInfo);
    }

    public List<ZhuanyeInfo> findAll() {
        //方法一：通过Java逻辑来给xueyuanName赋值

        List<ZhuanyeInfo> list = zhuanyeInfoDao.selectAll();
        for (ZhuanyeInfo zhuanyeInfo : list) {
            XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(zhuanyeInfo.getXueyuanId());  //返回学院信息
            zhuanyeInfo.setXueyuanName(xueyuanInfo.getName());  //赋值给自己设置的xueyuanName
        }
        return list;
    }


    public void update(ZhuanyeInfo zhuanyeInfo) {
        zhuanyeInfoDao.updateByPrimaryKeySelective(zhuanyeInfo);
    }

    public void deleteById(Long id) {
        zhuanyeInfoDao.deleteByPrimaryKey(id);
    }


    /* //旧的分页查询
     public PageInfo<ZhuanyeInfo> findPage(Integer pageNum, Integer pageSize) {
         PageHelper.startPage(pageNum, pageSize);  //开始分页
         List<ZhuanyeInfo> list = zhuanyeInfoDao.selectAll();
         return PageInfo.of(list);
     }*/
    //分页查询
    public PageInfo<ZhuanyeInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<ZhuanyeInfo> list = zhuanyeInfoDao.selectAll();

        for (ZhuanyeInfo zhuanyeInfo : list) {
            XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(zhuanyeInfo.getXueyuanId());  //返回学院信息
            zhuanyeInfo.setXueyuanName(xueyuanInfo.getName());  //赋值给自己设置的xueyuanName
        }

        return PageInfo.of(list);
    }


    /*///旧的按照用户名进行搜索
    public PageInfo<ZhuanyeInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<ZhuanyeInfo> zhuanyeInfos = zhuanyeInfoDao.findByLikeName(search);  //模糊查询
        return PageInfo.of(zhuanyeInfos); //返回值封装一下
    }*/
    ///按照用户名进行搜索
    public PageInfo<ZhuanyeInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<ZhuanyeInfo> zhuanyeInfos = zhuanyeInfoDao.findByLikeName(search);  //模糊查询

        for (ZhuanyeInfo zhuanyeInfo : zhuanyeInfos) {
            // 根据zhuanyeInfo里的xueyuanId，查询学院信息，然后拿到学院名称，赋值给xueyuanName字段
            if (ObjectUtil.isNotEmpty(zhuanyeInfo.getXueyuanId())) {
                XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(zhuanyeInfo.getXueyuanId());
                zhuanyeInfo.setXueyuanName(xueyuanInfo.getName());
            }
        }

        return PageInfo.of(zhuanyeInfos); //返回值封装一下
    }


    /*//搜索框模糊查询
    public List<ZhuanyeInfo> findSearch(String search) {
        //用Sql关联查询从数据库查询到xueyuanName
        return zhuanyeInfoDao.findBySearch(search);
    }*/

}
