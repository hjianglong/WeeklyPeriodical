package com.example.weeklyperiodical.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Collect implements Serializable {
    private Long id;
    private String username;
    private String collectlink;
    private String title;
}
