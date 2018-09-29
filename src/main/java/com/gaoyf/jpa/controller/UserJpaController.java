package com.gaoyf.jpa.controller;

import com.gaoyf.jpa.entity.User;
import com.gaoyf.jpa.service.UserJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 高宇飞 on 2018/9/27 16:42:05
 */
@RestController
@RequestMapping("/user-jpa")
public class UserJpaController {

    @Autowired
    private UserJpaService userJpaService;

    @RequestMapping("/getList")
    public List<User> getList() {
        return userJpaService.getList();
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Integer id) {
        return userJpaService.getUserById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(User user) {
        return userJpaService.save(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(Integer id) {
         userJpaService.delete(id);
    }

}
