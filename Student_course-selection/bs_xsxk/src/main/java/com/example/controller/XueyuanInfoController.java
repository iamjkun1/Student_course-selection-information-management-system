package com.example.controller;

import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.entity.XueyuanInfo;
import com.example.service.XueyuanInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

////controller 调用service层, service层与dao层相关联   controller是请求接口的入口
@RestController
@RequestMapping("/xueyuanInfo")
public class XueyuanInfoController {


    @Resource
    private XueyuanInfoService xueyuanInfoService;

    @GetMapping
    public Result findAll() {
        List<XueyuanInfo> list = xueyuanInfoService.findAll();
        return Result.success(list);
    }

    // 在 XueyuanInfoController 中添加一个新的接口，用来打印导出所有信息
    @GetMapping("/all")
    public Result find() {
        List<XueyuanInfo> list = xueyuanInfoService.findAll();
        return Result.success(list);
    }

    @PostMapping
    public Result add(@RequestBody XueyuanInfo xueyuanInfo) {
        xueyuanInfoService.add(xueyuanInfo);
        return Result.success();
    }

    //更新
    @PutMapping
    public Result update(@RequestBody XueyuanInfo xueyuanInfo) {
        xueyuanInfoService.update(xueyuanInfo);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        xueyuanInfoService.deleteById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<XueyuanInfo> pageInfo = xueyuanInfoService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    ///按照用户名进行搜索
    @GetMapping("/{search}")
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<XueyuanInfo> pageInfo = xueyuanInfoService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }

   /* //搜索框模糊查询
    @GetMapping("/{search}")
    public Result find(@PathVariable String search) {
        List<XueyuanInfo> list = xueyuanInfoService.find(search);
        return Result.success(list);
    }*/


}
