package com.imooc.miaosha.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String name;
    public User(int id, String name){
        this.id = id;
        this.name = name;
    }
}

