package com.example.controller;

import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.entity.ZhuanyeInfo;
import com.example.service.ZhaunyeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

////controller 调用service层, service层与dao层相关联   controller是请求接口的入口
@RestController
@RequestMapping("/zhuanyeInfo")
public class ZhuanyeInfoController {

    @Resource
    private ZhaunyeInfoService zhuanyeInfoService;

    @PostMapping
    public Result add(@RequestBody ZhuanyeInfo zhuanyeInfo) {
        zhuanyeInfoService.add(zhuanyeInfo);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        List<ZhuanyeInfo> list = zhuanyeInfoService.findAll();
        return Result.success(list);
    }

    // 在 ZhuanyeInfoController 中添加一个新的接口，用来打印导出所有信息
    @GetMapping("/all")
    public Result find() {
        List<ZhuanyeInfo> list = zhuanyeInfoService.findAll();
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody ZhuanyeInfo zhuanyeInfo) {

        zhuanyeInfoService.update(zhuanyeInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        zhuanyeInfoService.deleteById(id);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<ZhuanyeInfo> pageInfo = zhuanyeInfoService.findPage(pageNum, pageSize);
        return Result.success(pageInfo);
    }

    ///按照用户名进行搜索
    @GetMapping("/{search}")
    public Result findPageSearch(@PathVariable String search, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<ZhuanyeInfo> pageInfo = zhuanyeInfoService.findPageSearch(search, pageNum, pageSize);
        return Result.success(pageInfo);
    }



   /* //模糊查询
    @GetMapping("/{search}")
    public Result findSearch(@PathVariable String search) {
        List<ZhuanyeInfo> list = zhuanyeInfoService.findSearch(search);
        return Result.success(list);
    }*/

}
