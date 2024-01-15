package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Table;

///教师实体
@Table(name = "teacher_info")  ///与表映射
public class TeacherInfo extends Account{

    public String getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(String zhicheng) {
        this.zhicheng = zhicheng;
    }

    public String getZhuanyeId() {
        return zhuanyeId;
    }

    public void setZhuanyeId(String zhuanyeId) {
        this.zhuanyeId = zhuanyeId;
    }

    @Column(name = "zhicheng")  ////对于一列
    private String zhicheng;
    @Column(name = "zhuanyeId")
    private String zhuanyeId;


}
