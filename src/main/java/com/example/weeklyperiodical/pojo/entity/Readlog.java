package com.example.weeklyperiodical.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Readlog implements Serializable {

    private Long id;
    private Long type;
    private String ip;
    private String title;
    private String link;
    private String search;
    private String username;
}
