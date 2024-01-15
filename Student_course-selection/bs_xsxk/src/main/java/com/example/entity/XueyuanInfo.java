package com.example.entity;

import javax.persistence.*;

///相同属性的基类
@Table(name = "xueyuan_info")
public class XueyuanInfo {

    @Id   ///主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   ///自增
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "score")
    private Integer score;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
