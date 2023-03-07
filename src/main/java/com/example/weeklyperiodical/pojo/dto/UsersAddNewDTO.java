package com.example.weeklyperiodical.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 添加用户的DTO类
 */
@Data
public class UsersAddNewDTO implements Serializable {

    private String schoole;
    private String username;
    private String password;
    private Integer enable;
    private Long[] roleIds;


}
