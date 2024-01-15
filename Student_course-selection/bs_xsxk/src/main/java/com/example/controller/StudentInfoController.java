package com.example.controller;


import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.entity.StudentInfo;
import com.example.service.StudentInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

////controller 调用service层, service层与dao层相关联   controller是请求接口的入口
@RestController
@RequestMapping("/studentInfo")
public class StudentInfoController {

    @Resource
    private StudentInfoService studentInfoService;

    //新增
    @PostMapping
    public Result add(@RequestBody StudentInfo studentInfo) {
        studentInfoService.add(studentInfo);
        return Result.success();
    }

    //编辑更新
    @PutMapping
    public Result update(@RequestBody StudentInfo studentInfo) {
        studentInfoService.update(studentInfo);
        return Result.success();
    }

    //查询所有
    @GetMapping
    public Result findAll() {
        List<StudentInfo> list = studentInfoService.findAll();
        return Result.success(list);
    }

    // 在 StudentInfoController 中添加一个新的接口，用来打印导出所有信息
    @GetMapping("/all")
    public Result find() {
        List<StudentInfo> list = studentInfoService.findAll();
        return Result.success(list);
    }

    //删除信息
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        studentInfoService.deleteById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<StudentInfo> pageInfo = studentInfoService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    ///按照用户名进行搜索
    @GetMapping("/{search}")
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<StudentInfo> pageInfo = studentInfoService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }


  /*  //搜索框模糊查询
    @GetMapping("/{search}")
    public Result findSearch(@PathVariable String search) {
        List<StudentInfo> list = studentInfoService.findSearch(search);
        return Result.success(list);
    }*/


}
