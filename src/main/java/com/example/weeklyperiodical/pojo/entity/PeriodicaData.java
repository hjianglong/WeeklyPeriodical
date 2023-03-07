package com.example.weeklyperiodical.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PeriodicaData implements Serializable {
    private Long id;
    private String mgzname;
    private Long qkid;
    private String qs;
    private String mulu;
    private String title;
    private String text;
    private String mgztime;
    private String inittime;

}
