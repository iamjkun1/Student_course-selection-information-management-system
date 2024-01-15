package com.example.service;

import com.example.dao.ClassInfoDao;
import com.example.dao.TeacherInfoDao;
import com.example.dao.ZhuanyeInfoDao;
import com.example.entity.ClassInfo;
import com.example.entity.TeacherInfo;
import com.example.entity.ZhuanyeInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/////写业务逻辑层
@Service
public class ClassInfoService {

    @Resource
    private ClassInfoDao classInfoDao;
    @Resource
    private TeacherInfoDao teacherInfoDao;
    @Resource
    private ZhuanyeInfoDao zhuanyeInfoDao;

    //增
    public void add(ClassInfo classInfo) {
        classInfoDao.insertSelective(classInfo);
    }

    //查
    public List<ClassInfo> findAll() {

        //处理List，把每个信息对应教授ID转换成名称显示出来

        ///方式一：使用Java逻辑代码来实现关联查询
        List<ClassInfo> list = classInfoDao.selectAll();
        for (ClassInfo classInfo : list) {
            //根据classInfo里的teacherId，查询教师信息，然后拿到教师名称，赋值给teacherName字段
            TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(classInfo.getTeacherId());
            classInfo.setTeacherName(teacherInfo.getName());
        }

        for (ClassInfo classInfo: list) {
            ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(classInfo.getZhuanyeId());
            classInfo.setZhuanyeName(zhuanyeInfo.getName());
        }


        return list;
    }

    //改
    public void update(ClassInfo classInfo) {
        classInfoDao.updateByPrimaryKeySelective(classInfo);
    }

    //删
    public void delete(Long id) {
        classInfoDao.deleteByPrimaryKey(id);
    }

    /*//旧的分页查询
    public PageInfo<ClassInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);  //开始分页
        List<ClassInfo> list = classInfoDao.selectAll();
        return PageInfo.of(list);
    }*/
    //分页查询
    public PageInfo<ClassInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<ClassInfo> list = classInfoDao.selectAll();

        for (ClassInfo classInfo : list) {
            // 根据classInfo里的teacherId，查询教师信息，然后拿到教师名称，赋值给teacherName字段
            TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(classInfo.getTeacherId());
            classInfo.setTeacherName(teacherInfo.getName());

            ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(classInfo.getZhuanyeId());
            classInfo.setZhuanyeName(zhuanyeInfo.getName());
        }

        return PageInfo.of(list);
    }


    /*///旧的按照用户名进行搜索
    public PageInfo<ClassInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<ClassInfo> classInfos = classInfoDao.findByLikeName(search);  //模糊查询
        return PageInfo.of(classInfos); //返回值封装一下
    }*/
    ///按照用户名进行搜索
    public PageInfo<ClassInfo> findPageSearch(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
        List<ClassInfo> classInfos = classInfoDao.findByLikeName(search);  //模糊查询

        for (ClassInfo classInfo : classInfos) {
            // 根据classInfo里的teacherId，查询教师信息，然后拿到教师名称，赋值给teacherName字段
            TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(classInfo.getTeacherId());
            classInfo.setTeacherName(teacherInfo.getName());

            ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(classInfo.getZhuanyeId());
            classInfo.setZhuanyeName(zhuanyeInfo.getName());
        }


        return PageInfo.of(classInfos); //返回值封装一下
    }


   /* //模糊查询，搜索框
    public List<ClassInfo> findSearch(String search) {
        return classInfoDao.findSearch(search);
    }*/
}
