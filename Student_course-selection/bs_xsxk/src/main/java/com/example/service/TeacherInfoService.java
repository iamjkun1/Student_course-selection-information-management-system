package com.example.service;


import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.TeacherInfoDao;
import com.example.entity.Account;
import com.example.entity.TeacherInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/////写业务逻辑层
@Service
public class TeacherInfoService {
    ///引入Dao层
    @Resource
    private TeacherInfoDao teacherInfoDao;


/*    ///注册函数
    public void register(TeacherInfo teacherInfo) {
        //注册信息
        ///先判断同名
        TeacherInfo info = teacherInfoDao.findByName(teacherInfo.getName());

        if (ObjectUtil.isNotEmpty(info)) {
            //说明有用户了
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }

        //没有同名,往数据库插入数据
        teacherInfoDao.insertSelective(teacherInfo);
    }*/

    public Account login(String name, String password) {

        ///数据库查询密码等
        TeacherInfo teacherInfo = teacherInfoDao.findByNameAndPass(name, password);

        if (ObjectUtil.isEmpty(teacherInfo)) {
            throw new CustomException("-1", "用户名或密码错误");
        }
        return teacherInfo;
    }

    public TeacherInfo findById(Long id) {

        return teacherInfoDao.selectByPrimaryKey(id);   ///根据主键查找
    }

    public void update(TeacherInfo teacherInfo) {
        teacherInfoDao.updateByPrimaryKeySelective(teacherInfo);
    }

    //显示所有信息
    public List<TeacherInfo> findAll() {

        return teacherInfoDao.selectAll();

    }

    //增
    public void add(TeacherInfo teacherInfo) {
        ///插入时要考虑能否直接插

        //先查询有没有同名老师
        TeacherInfo info = teacherInfoDao.findByName(teacherInfo.getName());   //精确查询
        if (ObjectUtil.isNotEmpty(info)) {
            //查到会提示用户名已存在
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }  ////阻断

        if (ObjectUtil.isEmpty(teacherInfo.getPassword())) {
            //没有密码
            teacherInfo.setPassword("123456ab");   ///赋值初始化密码
        }
        teacherInfo.setLevel(2);
        teacherInfoDao.insertSelective(teacherInfo);
    }

    //删除操作
    public void deleteById(Long id) {
        teacherInfoDao.deleteByPrimaryKey(id);
    }

    //分页查询
    public PageInfo<TeacherInfo> findPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);  //开始分页
        List<TeacherInfo> list = teacherInfoDao.selectAll();
        return PageInfo.of(list);
    }

    ///按照用户名进行搜索
    public PageInfo<TeacherInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<TeacherInfo> teacherInfos = teacherInfoDao.findByLikeName(search);   //模糊查询
        return PageInfo.of(teacherInfos);   //返回值封装一下
    }
}

