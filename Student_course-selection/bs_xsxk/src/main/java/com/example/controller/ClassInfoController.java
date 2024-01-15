package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.ClassInfo;
import com.example.entity.XuankeInfo;
import com.example.exception.CustomException;
import com.example.service.ClassInfoService;
import com.example.service.XuankeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

////controller 调用service层, service层与dao层相关联   controller是请求接口的入口
@RestController
@RequestMapping("/classInfo")
public class ClassInfoController {

    @Resource
    private ClassInfoService classInfoService;
    @Resource
    private XuankeInfoService xuankeInfoService;

    @PostMapping
    public Result add(@RequestBody ClassInfo classInfo) {
        classInfoService.add(classInfo);
        return Result.success();
    }

    @PostMapping("/xuanke")
    public Result xuankeze(@RequestBody ClassInfo classInfo, HttpServletRequest request) {

        Account user = (Account) request.getSession().getAttribute("user");  //拿到当前用户信息
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录已失效，请重新登录!");
        }
        //1.判断该学生有没有选过该课程
        XuankeInfo info = xuankeInfoService.find(classInfo.getName(), classInfo.getTeacherId(), user.getId());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException("-1", "您已经选过该课程，请不要重复选择！");
        }

        //2.把课程信息表塞一份到选课信息表中
        XuankeInfo xuankeInfo = new XuankeInfo();
        BeanUtils.copyProperties(classInfo, xuankeInfo);  //把课程信息copy给选课信息表
        xuankeInfo.setId(null);   //防止Id冲突

        //3把选课信息表里剩下的字段信息补全
        xuankeInfo.setStudentId(user.getId());   //赋值Id
        xuankeInfo.setStatus("待开课");

        xuankeInfoService.add(xuankeInfo);

        //课程信息里的已选人数加一
        classInfo.setYixuan(classInfo.getYixuan() + 1);
        classInfoService.update(classInfo);  //跟新课程信息，已经+1了

        return Result.success();
    }

    //编辑功能实现---update
    @PutMapping
    public Result update(@RequestBody ClassInfo classInfo) {
        classInfoService.update(classInfo);
        return Result.success();
    }

    //显示所有信息
    @GetMapping
    public Result findAll() {
        List<ClassInfo> list = classInfoService.findAll();
        return Result.success(list);
    }

    // 在 ClassInfoController 中添加一个新的接口，用来打印导出所有信息
    @GetMapping("/all")
    public Result find() {
        List<ClassInfo> list = classInfoService.findAll();
        return Result.success(list);
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        classInfoService.delete(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<ClassInfo> pageInfo = classInfoService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    ///按照用户名进行搜索
    @GetMapping("/{search}")
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<ClassInfo> pageInfo = classInfoService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }

   /* //搜索框模糊查询
    @GetMapping("/{search}")
    public Result findSearch(@PathVariable String search) {
        List<ClassInfo> list = classInfoService.findSearch(search);
        return Result.success(list);
    }*/

}
