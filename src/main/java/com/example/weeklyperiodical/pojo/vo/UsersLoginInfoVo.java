package com.example.weeklyperiodical.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UsersLoginInfoVo implements Serializable {

    private Long uid;
    private String username;
    private String password;
    private Integer enable;
    private List<String> permissions;


}
