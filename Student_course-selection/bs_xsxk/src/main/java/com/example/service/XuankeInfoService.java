package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.dao.*;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/////写业务逻辑层
@Service
public class XuankeInfoService {

    @Resource
    private XuankeInfoDao xuankeInfoDao;
    @Resource
    private ZhuanyeInfoDao zhaunyeInfoDao;
    @Resource
    private TeacherInfoDao teacherInfoDao;
    @Resource
    private StudentInfoDao studentInfoDao;
    @Resource
    private ClassInfoDao classInfoDao;

    public List<XuankeInfo> findAll(HttpServletRequest request) {  ///查询所有选课信息，包括所有学生的选课信息
        Account user = (Account) request.getSession().getAttribute("user");  //拿到当前用户
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录已失效，请重新登录!");
        }
        List<XuankeInfo> list = xuankeInfoDao.selectAll();
//        = xuankeInfoDao.findByCondition();  ///根据主键查询个人信息,如教师看到教师个人的信息
        //判断用户是那种角色
/*        if (1 == user.getLevel()) {  //管理员
            list = xuankeInfoDao.selectAll();  //管理员直接看全部信息

//            list = xuankeInfoDao.findByCondition(user.getId(), user.getId());;  //管理员直接看全部信息
        } else */
        if (2 == user.getLevel()) {  //教师
            list = xuankeInfoDao.findByCondition(user.getId(), null);
        } else if (3 == user.getLevel()) {  //学生
            list = xuankeInfoDao.findByCondition(null, user.getId());
        }


        for (XuankeInfo xuankeInfo : list) {
            ZhuanyeInfo zhuanyeInfo = zhaunyeInfoDao.selectByPrimaryKey(xuankeInfo.getZhuanyeId());   //选课表得到专业ID
            TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(xuankeInfo.getTeacherId());
            StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getStudentId());
            //得到信息，塞进选课信息表中
            xuankeInfo.setZhuanyeName(zhuanyeInfo.getName());
            xuankeInfo.setTeacherName(teacherInfo.getName());
            xuankeInfo.setStudentName(studentInfo.getName());
        }
        return list;
    }

    public void add(XuankeInfo xuankeInfo) {
        xuankeInfoDao.insertSelective(xuankeInfo);  //把选课信息插入选课表
    }

    //判断学生是否选过本课程
    public XuankeInfo find(String name, Long teacherId, Long studentId) {

        return xuankeInfoDao.find(name, teacherId, studentId);
    }

    //取消选课
    public void delete(Long id) {
        XuankeInfo xuankeInfo = xuankeInfoDao.selectByPrimaryKey(id);  //把这门可选课信息查出来
        ClassInfo classInfo = classInfoDao.findByNameAndTeacher(xuankeInfo.getName(), xuankeInfo.getTeacherId());  //拿到课程记录
        xuankeInfoDao.deleteByPrimaryKey(id); //删除
        classInfo.setYixuan(classInfo.getYixuan() - 1);  //已选人数-1
        classInfoDao.updateByPrimaryKeySelective(classInfo); //更新课程表
    }

    //开课功能更新
    public void update(XuankeInfo xuankeInfo) {
        xuankeInfoDao.updateByPrimaryKeySelective(xuankeInfo);
    }

    //分页查询
    public PageInfo<XuankeInfo> findPage(Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);  //开始分页

        Account user = (Account) request.getSession().getAttribute("user");  //拿到当前用户
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录已失效，请重新登录!");
        }
        List<XuankeInfo> list = xuankeInfoDao.selectAll();
//        = xuankeInfoDao.findByCondition();  ///根据主键查询个人信息,如教师看到教师个人的信息
        //判断用户是那种角色
/*        if (1 == user.getLevel()) {  //管理员
            list = xuankeInfoDao.selectAll();  //管理员直接看全部信息
//            list = xuankeInfoDao.findByCondition(user.getId(),user.getId());  //管理员直接看全部信息
        } else */
        if (2 == user.getLevel()) {  //教师
            list = xuankeInfoDao.findByCondition(user.getId(), null);
        } else if (3 == user.getLevel()) {  //学生
            list = xuankeInfoDao.findByCondition(null, user.getId());
        }


        for (XuankeInfo xuankeInfo : list) {
            //老师id转名称
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherId())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(xuankeInfo.getTeacherId());
                xuankeInfo.setTeacherName(teacherInfo.getName());
            }
            //专业id转名称
            if (ObjectUtil.isNotEmpty(xuankeInfo.getZhuanyeId())) {
                ZhuanyeInfo zhuanyeInfo = zhaunyeInfoDao.selectByPrimaryKey(xuankeInfo.getZhuanyeId());
                xuankeInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            //学生id转名称
            if (ObjectUtil.isNotEmpty(xuankeInfo.getStudentId())) {
                StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getStudentId());
                /*System.out.println(studentInfo.getName());*/
                    xuankeInfo.setStudentName(studentInfo.getName());
            }
        }

        return PageInfo.of(list);
    }

    ///按照用户名进行搜索
    public PageInfo<XuankeInfo> findPageSearch(String search, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);   //开始分页
//        List<XuankeInfo> xuankeInfos = xuankeInfoDao.findByLikeName(search);  //模糊查询

        Account user = (Account) request.getSession().getAttribute("user");  //拿到当前用户
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录已失效，请重新登录!");
        }
//        List<XuankeInfo> list;
        List<XuankeInfo> xuankeInfos = xuankeInfoDao.findByLikeName(search);  //模糊查询
        //判断用户是那种角色
/*        if (1 == user.getLevel()) {  //管理员
            xuankeInfos = xuankeInfoDao.selectAll();  //管理员直接看全部信息
//            xuankeInfos = xuankeInfoDao.findByCondition(user.getId(),user.getId());  //管理员直接看全部信息
        } else */
        if (2 == user.getLevel()) {  //教师
            xuankeInfos = xuankeInfoDao.findByCondition(user.getId(), null);
        } else if (3 == user.getLevel()) {  //学生
            xuankeInfos = xuankeInfoDao.findByCondition(null, user.getId());
        }


        for (XuankeInfo xuankeInfo : xuankeInfos) {
            //老师id转名称
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherId())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(xuankeInfo.getTeacherId());
                xuankeInfo.setTeacherName(teacherInfo.getName());
            }
            //专业id转名称
            if (ObjectUtil.isNotEmpty(xuankeInfo.getZhuanyeId())) {
                ZhuanyeInfo zhuanyeInfo = zhaunyeInfoDao.selectByPrimaryKey(xuankeInfo.getZhuanyeId());
                xuankeInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            //学生id转名称
            if (ObjectUtil.isNotEmpty(xuankeInfo.getStudentId())) {
                StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getStudentId());
                xuankeInfo.setStudentName(studentInfo.getName());
            }

        }
        return PageInfo.of(xuankeInfos); //返回值封装一下
    }
}
