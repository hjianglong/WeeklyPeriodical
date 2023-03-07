package com.example.weeklyperiodical.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsersLoginDTO implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（原文）
     */
    private String password;
}
