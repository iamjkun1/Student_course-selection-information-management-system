package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.AdminInfoDao;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/////写业务逻辑层
@Service
public class AdminInfoService {

    @Resource
    private AdminInfoDao adminInfoDao;

    public Account login(String name, String password) {
        ///数据库查询密码等
        AdminInfo adminInfo = adminInfoDao.findByNameAndPass(name, password);

        if (ObjectUtil.isEmpty(adminInfo)) {
            throw new CustomException("-1", "用户名或密码错误");
        }
        return adminInfo;
    }

    public AdminInfo findById(Long id) {

        return adminInfoDao.selectByPrimaryKey(id);

    }


    //更新
    public void update(AdminInfo adminInfo) {
        adminInfoDao.updateByPrimaryKey(adminInfo);
    }

    ///增加管理员
    public void add(AdminInfo adminInfo) {

        ///插入时要考虑能否直接插

        //先查询有没有同名管理员
        AdminInfo info = adminInfoDao.findByName(adminInfo.getName());
        if (ObjectUtil.isNotEmpty(info)) {
            //查到会提示用户名已存在
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }  ////阻断

        if (ObjectUtil.isEmpty(adminInfo.getPassword())) {
            //没有密码
            adminInfo.setPassword("123456ab");   ///赋值初始化密码
        }

        adminInfoDao.insertSelective(adminInfo);
    }

    public List<AdminInfo> findAll() {

        return adminInfoDao.selectAll();
    }

    public void deleteById(Long id) {

        adminInfoDao.deleteByPrimaryKey(id);
    }


    ///分页查询
    public PageInfo<AdminInfo> findPage(Integer pageNum, Integer pageSize) {
        //开始分页
        PageHelper.startPage(pageNum, pageSize);
        //下面会根据规定参数查询
        List<AdminInfo> infos = adminInfoDao.selectAll();

        return PageInfo.of(infos);

    }

    public PageInfo<AdminInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开始分页
        PageHelper.startPage(pageNum, pageSize);
        List<AdminInfo> infos = adminInfoDao.findByNamePage(name);
        return PageInfo.of(infos);

    }
}
