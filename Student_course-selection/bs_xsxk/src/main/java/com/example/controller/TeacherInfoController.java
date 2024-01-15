package com.example.controller;

import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.entity.TeacherInfo;
import com.example.service.TeacherInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

////controller 调用service层, service层与dao层相关联   controller是请求接口的入口
@RestController
@RequestMapping("/teacherInfo")
public class TeacherInfoController {

    @Resource
    private TeacherInfoService teacherInfoService;

    @PostMapping
    public Result add(@RequestBody TeacherInfo teacherInfo) {
        teacherInfoService.add(teacherInfo);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody TeacherInfo teacherInfo) {

        teacherInfoService.update(teacherInfo);
        return Result.success();
    }

    //显示所有信息
    @GetMapping
    public Result findAll() {
        List<TeacherInfo> list = teacherInfoService.findAll();
        return Result.success(list);
    }

    // 在 TeacherInfoController 中添加一个新的接口，用来打印导出所有信息
    @GetMapping("/all")
    public Result find() {
        List<TeacherInfo> list = teacherInfoService.findAll();
        return Result.success(list);
    }

    //删除信息
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        teacherInfoService.deleteById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<TeacherInfo> pageInfo = teacherInfoService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    ///按照用户名进行搜索
    @GetMapping("/{search}")
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<TeacherInfo> pageInfo = teacherInfoService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
