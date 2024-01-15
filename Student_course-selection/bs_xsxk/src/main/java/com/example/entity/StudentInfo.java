package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

///教师实体
@Table(name = "student_info")  ///与表映射
public class StudentInfo extends Account {

    @Column(name = "code")  ////对于一列
    private Long code;
    @Column(name = "xueyuanId")
    private Long xueyuanId;
    @Column(name = "score")
    private Integer score;

    @Transient
    private String xueyuanName;  ///声明存储数据用的，不是数据库字段

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getXueyuanId() {
        return xueyuanId;
    }

    public void setXueyuanId(Long xueyuanId) {
        this.xueyuanId = xueyuanId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getXueyuanName() {
        return xueyuanName;
    }

    public void setXueyuanName(String xueyuanName) {
        this.xueyuanName = xueyuanName;
    }
}
