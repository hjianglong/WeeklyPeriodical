package com.example.weeklyperiodical.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CollectVO implements Serializable {
    private Long id;
    private String username;
    private String collectlink;
    private String title;
    private LocalDateTime createtime;
    private LocalDateTime endtime;
}
