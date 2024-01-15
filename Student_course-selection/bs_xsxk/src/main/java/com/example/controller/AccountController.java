package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.StudentInfo;
import com.example.entity.TeacherInfo;
import com.example.service.AdminInfoService;
import com.example.service.StudentInfoService;
import com.example.service.TeacherInfoService;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 跟账号相关的描述*/
@RestController
@RequestMapping
public class AccountController {
    @Resource
    private AdminInfoService adminInfoService;
    @Resource
    private TeacherInfoService teacherInfoService;
    @Resource
    private StudentInfoService studentInfoService;

    ///验证码
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //png类型
        SpecCaptcha captcha = new SpecCaptcha(135, 33, 4);
        captcha.setCharType(SpecCaptcha.TYPE_ONLY_NUMBER);
        CaptchaUtil.out(captcha, request, response);

        //算术类型
        /*ArithmeticCaptcha captcha = new ArithmeticCaptcha(134, 35);
        captcha.setLen(3);   //几位数运算公式
        captcha.getArithmeticString(); ///公式如 ：3+1=？
        captcha.text();  //获得结果：4
        CaptchaUtil.out(captcha, request, response);*/

    }

    ///登录
    @PostMapping("/login")
    public Result login(@RequestBody Account user, HttpServletRequest request) {

        //校验数据有没有填写
        if (ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善填写信息");
        }

        ///先判断验证码正确与否
        if (!CaptchaUtil.ver(user.getVerCode(), request)) {
            //清除session中的验证码
            CaptchaUtil.clear(request);
            return Result.error("1001", "验证码错误!");
        }

        Integer level = user.getLevel();
        Account loginUser = new Account();  //统一接收登录信息
        if (1 == level) {
            //管理员登录
            loginUser = adminInfoService.login(user.getName(), user.getPassword());
        }
        if (2 == level) {
            //教师登录
            loginUser = teacherInfoService.login(user.getName(), user.getPassword());
        }
        if (3 == level) {
            //学生登录
            loginUser = studentInfoService.login(user.getName(), user.getPassword());
        }


        //在session里把用户信息存一份
        request.getSession().setAttribute("user", loginUser);

        return Result.success(loginUser);
    }

    ///注册功能
    @PostMapping("/register")
    public Result register(@RequestBody Account user, HttpServletRequest request) {

        //校验数据有没有填写
        if (ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善填写信息");
        }

        Integer level = user.getLevel();
        if (2 == level) {
            //教师注册
            TeacherInfo teacherInfo = new TeacherInfo();
            BeanUtils.copyProperties(user, teacherInfo);  //从父类account一些数据字段 copy 一份
            teacherInfoService.add(teacherInfo);   ///注册时调用新增函数，把数据新增进去
        }
        if (3 == level) {
            //学生注册
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(user, studentInfo);
            studentInfoService.add(studentInfo);  ///注册时调用新增函数，把数据新增进去
        }

        return Result.success();
    }

    //获得当前用户,显示个人信息
    @GetMapping("/getUser")
    public Result getUser(HttpServletRequest request) {

        //从session获得用户信息
        Account user = (Account) request.getSession().getAttribute("user");

        //判断用户类型
        Integer level = user.getLevel();
//        Account loginUser = new Account();  //统一接收登录信息
        if (1 == level) {
            //获取管理员
            AdminInfo adminInfo = adminInfoService.findById(user.getId());
            return Result.success(adminInfo);
        }
        if (2 == level) {
            //获取教师
            TeacherInfo teacherInfo = teacherInfoService.findById(user.getId());
            teacherInfo.setPassword("");  //把密码设置显示为空，防止网页泄露
            return Result.success(teacherInfo);
        }
        if (3 == level) {
            //获取学生
            StudentInfo studentInfo = studentInfoService.findById(user.getId());
            return Result.success(studentInfo);
        }

        return Result.success(new Account());
    }

    //修改密码
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account, HttpServletRequest request) {
        //1.判断用户角色
        //从session获得用户信息
        Account user = (Account) request.getSession().getAttribute("user");  //获得当前用户信息
        //判断用户类型
        //2.根据角色去数据库表更新密码
        //获得原密码和新密码，并判断正误
        String oldPassword = account.getPassword();  //原来用户用的密码
        if (!user.getPassword().equals(oldPassword)) {
            return Result.error("-1", "原密码输入错误");
        }
        //验证通过是用户本人
        String newPassword = account.getNewPassword();
        Integer level = user.getLevel();
        if (1 == level) {
            //管理员修改密码
            AdminInfo adminInfo = new AdminInfo();  //new一个新的管理员
            BeanUtils.copyProperties(user, adminInfo);  //把user copy 到管理员里
            adminInfo.setPassword(newPassword);   //更新密码
            adminInfoService.update(adminInfo); //更新数据
        }
        if (2 == level) {
            //教师修改密码
            TeacherInfo teacherInfo = new TeacherInfo();
            BeanUtils.copyProperties(user, teacherInfo);
            teacherInfo.setPassword(newPassword);
            teacherInfoService.update(teacherInfo);
        }
        if (3 == level) {
            //学生修改密码
            StudentInfo studentInfo = new StudentInfo();
            BeanUtils.copyProperties(user, studentInfo);
            studentInfo.setPassword(newPassword);
            studentInfoService.update(studentInfo);
        }
        //3.把session中登录用户信息删除
        request.getSession().setAttribute("user", null);
        return Result.success();
    }

    //退出登录
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);   ///清空用户
        return Result.success();
    }
}
