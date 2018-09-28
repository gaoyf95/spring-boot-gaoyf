package com.gaoyf.resultful.controller;

import com.gaoyf.redis.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 高宇飞 on 2018/9/27 15:10:47
 */
@RestController
public class Hello {

    @RequestMapping(value = "/hello")
    public String index() {
        RedisUtil.set("abc", "1234",5);
        return RedisUtil.get("abc").toString() + "---" + RedisUtil.getExpire("abc");
    }

}
