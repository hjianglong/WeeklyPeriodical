package com.example.weeklyperiodical.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UsersRole implements Serializable {

    private Long id;
    private Long usersid;
    private Long roleid;
    private LocalDateTime createtime;
    private LocalDateTime endtime;

}
