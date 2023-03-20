package com.example.weeklyperiodical.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PeriodicaDataQueryVO implements Serializable {

    private Long id;
    private String mgzname;
    private Long qkid;
    private String qs;
    private String mulu;
    private String title;
    private String text;

}
