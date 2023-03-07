package com.example.weeklyperiodical.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersListItemVO implements Serializable {
    private Long uid;
    private String schoole;
    private String username;
    private String beforepwd;
    private String model;
    private String selclassify;
    private Integer enable;
}
