package com.example.controller;

import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.service.AdminInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

////controller 调用service层, service层与dao层相关联   controller是请求接口的入口
@RestController
@RequestMapping("/adminInfo")
public class AdminInfoController {

    @Resource
    private AdminInfoService adminInfoService;

    ///添加管理员
    @PostMapping
    public Result add(@RequestBody AdminInfo adminInfo) {
        adminInfoService.add(adminInfo);
        return Result.success();
    }

    //修改个人信息
    @PutMapping
    public Result update(@RequestBody AdminInfo adminInfo) {
        adminInfoService.update(adminInfo);
        return Result.success();
    }

    //显示所有管理员信息
    @GetMapping
    public Result findAll() {
        List<AdminInfo> list = adminInfoService.findAll();
        return Result.success(list);
    }

    // 在 AdminInfoController 中添加一个新的接口，用来打印导出所有信息
    @GetMapping("/all")
    public Result find() {
        List<AdminInfo> list = adminInfoService.findAll();
        return Result.success(list);
    }


    //删除管理员信息
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Long id) {
        adminInfoService.deleteById(id);

        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {

        PageInfo<AdminInfo> info = adminInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }

    //搜索框分页查询
    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize, @PathVariable String name) {

        PageInfo<AdminInfo> info = adminInfoService.findPageName(pageNum, pageSize, name);
        return Result.success(info);
    }


}
