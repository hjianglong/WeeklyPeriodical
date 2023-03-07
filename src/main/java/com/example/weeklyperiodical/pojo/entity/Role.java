package com.example.weeklyperiodical.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Role implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Integer sort;
    private LocalDateTime createtime;
    private LocalDateTime endtime;
}
