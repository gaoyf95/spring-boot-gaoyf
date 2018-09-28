package com.gaoyf.resultful.controller;

import com.gaoyf.resultful.entity.User;
import com.gaoyf.resultful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 高宇飞 on 2018/9/27 16:42:05
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    public List<User> getList() {
        return userService.getList();
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/getUserById2", method = RequestMethod.GET)
    public User getUserById2(@NotNull(message = "id不能为空") Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(@Valid User user) {
        return userService.save(user);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Map handleConstraintViolationException(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> cves = cve.getConstraintViolations();
        Map map = new HashMap();
        for (ConstraintViolation<?> constraintViolation : cves) {
            map.put("message", constraintViolation.getMessage());
        }
        return map;
    }

}
