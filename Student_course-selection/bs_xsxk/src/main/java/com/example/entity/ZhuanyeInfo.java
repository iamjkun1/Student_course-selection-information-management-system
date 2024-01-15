package com.example.entity;

import javax.persistence.*;

///相同属性的基类
@Table(name = "zhuanye_info")
public class ZhuanyeInfo {

    @Id   ///主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   ///自增
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;
    @Column(name = "xueyuanId")
    private Long xueyuanId;
    @Transient
    private String xueyuanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getXueyuanId() {
        return xueyuanId;
    }

    public void setXueyuanId(Long xueyuanId) {
        this.xueyuanId = xueyuanId;
    }

    public String getXueyuanName() {
        return xueyuanName;
    }

    public void setXueyuanName(String xueyuanName) {
        this.xueyuanName = xueyuanName;
    }
}
