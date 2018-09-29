package com.gaoyf.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 高宇飞 on 2018/9/27 17:21:38
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
