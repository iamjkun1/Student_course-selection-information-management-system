package com.example.service;


import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.StudentInfoDao;
import com.example.dao.XueyuanInfoDao;
import com.example.entity.Account;
import com.example.entity.StudentInfo;
import com.example.entity.XueyuanInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/////写业务逻辑层
@Service
public class StudentInfoService {
    ///引入Dao层
    @Resource
    private StudentInfoDao studentInfoDao;
    @Resource
    private XueyuanInfoDao xueyuanInfoDao;


    //登录
    public Account login(String name, String password) {
        ///数据库查询密码等
        StudentInfo studentInfo = studentInfoDao.findByNameAndPassword(name, password);

        if (ObjectUtil.isEmpty(studentInfo)) {
            throw new CustomException("-1", "用户名或密码错误");
        }
        return studentInfo;

    }

/*    //学生注册
    public void register(StudentInfo studentInfo) {

        //注册信息
        ///先判断同名
        StudentInfo info = studentInfoDao.findByName(studentInfo.getName());

        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }

        //没有同名,往数据库插入数据
        studentInfoDao.insertSelective(studentInfo);
    }*/

    ///查询用户主键
    public StudentInfo findById(Long id) {

        return studentInfoDao.selectByPrimaryKey(id);
    }

    ///更新个人信息
    public void update(StudentInfo studentInfo) {
        studentInfoDao.updateByPrimaryKeySelective(studentInfo);
    }

    //查询所有
    public List<StudentInfo> findAll() {


        //处理List，把每个信息对应学院名称ID转换成名称显示出来

        ///方式一：使用Java逻辑代码来实现关联查询
        List<StudentInfo> list = studentInfoDao.selectAll();
        for (StudentInfo studentInfo : list) {
            //根据studentInfo里的xueyuanId，查询学院信息，然后拿到学院名称，赋值给xueyuanName字段
            if (ObjectUtil.isNotEmpty(studentInfo.getXueyuanId())) {
                XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getXueyuanId());
                studentInfo.setXueyuanName(xueyuanInfo.getName());

            }

        }
        return list;

        ///方式二：使用Sql关联查询语句直接查
//        List<StudentInfo> list2 = studentInfoDao.findAllJoinXueyuan();
//
//        return list2;
//        return studentInfoDao.findAllJoinXueyuan();

    }

    //新增
    public void add(StudentInfo studentInfo) {
        //新增信息
        ///先判断同名
        StudentInfo info = studentInfoDao.findByName(studentInfo.getName());

        if (ObjectUtil.isNotEmpty(info)) {  //同名了
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }

        //没有密码，初始化一个密码
        if (ObjectUtil.isEmpty(studentInfo.getPassword())) {
            studentInfo.setPassword("123456ab");
        }
        studentInfoDao.insertSelective(studentInfo);
    }

    public void deleteById(Long id) {
        studentInfoDao.deleteByPrimaryKey(id);
    }

    /*//旧的分页查询
    public PageInfo<StudentInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);  //开始分页
        List<StudentInfo> list = studentInfoDao.selectAll();
        return PageInfo.of(list);
    }*/

    //分页查询
    public PageInfo<StudentInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);  //开始分页
//        List<StudentInfo> list = studentInfoDao.selectAll();

        List<StudentInfo> list = studentInfoDao.selectAll();
        for (StudentInfo studentInfo : list) {
            //根据studentInfo里的xueyuanId，查询学院信息，然后拿到学院名称，赋值给xueyuanName字段
            if (ObjectUtil.isNotEmpty(studentInfo.getXueyuanId())) {
                XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getXueyuanId());
                studentInfo.setXueyuanName(xueyuanInfo.getName());

            }

        }
        return PageInfo.of(list);
    }


    /*///旧的按照用户名进行搜索
    public PageInfo<StudentInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<StudentInfo> studentInfos = studentInfoDao.findByLikeName(search);  //模糊查询
        return PageInfo.of(studentInfos); //返回值封装一下
    }*/

    public PageInfo<StudentInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<StudentInfo> studentInfos = studentInfoDao.findByLikeName(search);  //模糊查询

        for (StudentInfo studentInfo : studentInfos) {
            // 根据studentInfo里的xueyuanId，查询学院信息，然后拿到学院名称，赋值给xueyuanName字段
            if (ObjectUtil.isNotEmpty(studentInfo.getXueyuanId())) {
                XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getXueyuanId());
                studentInfo.setXueyuanName(xueyuanInfo.getName());
            }
        }

        return PageInfo.of(studentInfos); //返回值封装一下
    }



   /* //搜索框模糊查询
    public List<StudentInfo> findSearch(String search) {
        //用Sql关联查询从数据库查询到xueyuanName
        return studentInfoDao.findBySearch(search);
    }*/
}

