package com.example.controller;

import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.entity.XuankeInfo;
import com.example.service.XuankeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

////controller 调用service层, service层与dao层相关联   controller是请求接口的入口
@RestController
@RequestMapping("/xuankeInfo")
public class XuankeInfoController {

    @Resource
    private XuankeInfoService xuankeInfoService;

    //所有选课信息显示面板
    @GetMapping
    public Result findAll(HttpServletRequest request) {   //精准到查个人，不会看到全部人
        List<XuankeInfo> list = xuankeInfoService.findAll(request);
        return Result.success(list);
    }

    // 在 XuankeInfoController 中添加一个新的接口，用来打印导出所有信息
    @GetMapping("/all")
    public Result find(HttpServletRequest request) {
        List<XuankeInfo> list = xuankeInfoService.findAll(request);
        return Result.success(list);
    }

    //取消选课功能
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        xuankeInfoService.delete(id);
        return Result.success();
    }

    //开课功能
    @PutMapping
    public Result update(@RequestBody XuankeInfo xuankeInfo) {
        xuankeInfoService.update(xuankeInfo);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,HttpServletRequest request) {
        PageInfo<XuankeInfo> pageInfo = xuankeInfoService.findPage(pageNum, pageSize,request);
        return Result.success(pageInfo);
        /*return Result.success("成功");*/
    }

    ///按照用户名进行搜索,搜索框模糊查询
    @GetMapping("/{search}")
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize,HttpServletRequest request) {
        PageInfo<XuankeInfo> pageInfo = xuankeInfoService.findPageSearch(search, pageNum, pageSize,request);
        return Result.success(pageInfo);
    }

}
