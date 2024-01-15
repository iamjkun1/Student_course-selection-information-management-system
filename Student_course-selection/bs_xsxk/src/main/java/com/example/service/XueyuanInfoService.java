package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.dao.XueyuanInfoDao;
import com.example.entity.XueyuanInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/////写业务逻辑层
@Service
public class XueyuanInfoService {

    @Resource
    private XueyuanInfoDao xueyuanInfoDao;

    public void add(XueyuanInfo xueyuanInfo) {
//        //学分校验不能为空--后台校验
//        if (ObjectUtil.isEmpty(xueyuanInfo.getScore())) {
//            throw new CustomException("-1", "学分限制不能为空");
//        }
        XueyuanInfo info = xueyuanInfoDao.findByName(xueyuanInfo.getName());   //判断重名情况
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException("-1", "该学院名称已存在");
        }
        xueyuanInfoDao.insertSelective(xueyuanInfo);
    }

    //查询所有
    public List<XueyuanInfo> findAll() {

        return xueyuanInfoDao.selectAll();
    }

    //编辑功能：更新
    public void update(XueyuanInfo xueyuanInfo) {
        xueyuanInfoDao.updateByPrimaryKeySelective(xueyuanInfo);   //改了什么就更新什么
    }

    //删除
    public void deleteById(Long id) {
        xueyuanInfoDao.deleteByPrimaryKey(id);
    }


    //分页查询
    public PageInfo<XueyuanInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);  //开始分页
        List<XueyuanInfo> list = xueyuanInfoDao.selectAll();
        return PageInfo.of(list);
    }

    ///按照用户名进行搜索
    public PageInfo<XueyuanInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<XueyuanInfo> xueyuanInfos = xueyuanInfoDao.findByLikeName(search);  //模糊查询
        return PageInfo.of(xueyuanInfos); //返回值封装一下
    }

   /* //搜索框查询
    public List<XueyuanInfo> find(String search) {
        return xueyuanInfoDao.find(search);
    }*/
}
