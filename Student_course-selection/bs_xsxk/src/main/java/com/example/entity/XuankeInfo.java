package com.example.entity;

import javax.persistence.*;

///相同属性的基类
@Table(name = "xuanke_info")
public class XuankeInfo {

    @Id   ///主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)   ///自增
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "score")
    private Integer score;
    @Column(name = "kaiban")
    private Integer kaiban;
    @Column(name = "time")
    private String time;
    @Column(name = "location")
    private String location;

    @Column(name = "zhuanyeId")
    private Long zhuanyeId;
    @Column(name = "teacherId")
    private Long teacherId;
    @Column(name = "studentId")
    private Long studentId;
    @Column(name = "status")
    private String status;
    @Transient
    private String teacherName;
    @Transient
    private String zhuanyeName;
    @Transient
    private String studentName;

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

    public Integer getKaiban() {
        return kaiban;
    }

    public void setKaiban(Integer kaiban) {
        this.kaiban = kaiban;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getZhuanyeId() {
        return zhuanyeId;
    }

    public void setZhuanyeId(Long zhuanyeId) {
        this.zhuanyeId = zhuanyeId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getZhuanyeName() {
        return zhuanyeName;
    }

    public void setZhuanyeName(String zhuanyeName) {
        this.zhuanyeName = zhuanyeName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
