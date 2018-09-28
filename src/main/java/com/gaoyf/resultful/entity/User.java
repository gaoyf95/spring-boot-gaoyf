package com.gaoyf.resultful.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created by 高宇飞 on 2018/9/27 17:21:38
 */
@Data
public class User {
    private Integer id;
    @NotNull(message = "名称不能为空")
    @Length(max = 10,message = "名称最大长度为10")
    private String name;
}
