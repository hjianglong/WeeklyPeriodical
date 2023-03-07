package com.example.weeklyperiodical.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Users implements Serializable {
    private Long uid;
    private String schoole;
    private String username;
    private String password;
    private String beforepwd;
    private String model;
    private String selclassify;
    private LocalDateTime createtime;
    private LocalDateTime endtime;
    private Integer enable;
}
