package com.example.weeklyperiodical.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Periodical implements Serializable {
    private Long id;
    private String period;
    private String IndexImg;
    private String sponsor;
    private String title;
    private String synopsis;
    private String createyear;
    private String cbd;
    private String qs;
    private String bdb;
    private String zjmc;
    private String ztmc;


}
