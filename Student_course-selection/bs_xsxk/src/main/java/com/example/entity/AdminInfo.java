package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Table;

///管理员实体
@Table(name = "admin_info")  ///与表映射
public class AdminInfo extends Account{

    @Column(name = "phone")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
