package com.example.weeklyperiodical.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReadlogAddNewDTO implements Serializable {

    private Long type;
    private String ip;
    private String title;
    private String link;
    private String search;
    private String username;
}
