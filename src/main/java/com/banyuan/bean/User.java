package com.banyuan.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
}
