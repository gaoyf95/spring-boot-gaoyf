package com.gaoyf.jpa.controller;

import com.gaoyf.common.VO.ExceptionEnum;
import com.gaoyf.common.exception.BusinessException;
import com.gaoyf.config.interfaces.Function;
import com.gaoyf.config.interfaces.Module;
import com.gaoyf.jpa.entity.User;
import com.gaoyf.jpa.service.UserJpaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 高宇飞 on 2018/9/27 16:42:05
 */
@RestController
@RequestMapping("/user-jpa")
@Api(tags = "user-jpa", description = "用户操作")
@Module("用户管理")
public class UserJpaController {

    @Autowired
    private UserJpaService userJpaService;

    @GetMapping("/getList")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @Function("获取用户列表")
    public List<User> getList() {
        return userJpaService.getList();
    }

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable String id) {
        return userJpaService.getUserById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(User user) {
        return userJpaService.save(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete(String id) {
        userJpaService.delete(id);
        if (1 == 1) {
            //测试业务异常
            throw new BusinessException(ExceptionEnum.PARAM_NULL);
        }
    }

    /**
     * 拦截验证之后的异常信息  可以写到公共拦截器中
     *
     * @param cve
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Map handleConstraintViolationException(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> set = cve.getConstraintViolations();
        Map map = new HashMap();
        for (ConstraintViolation<?> constraintViolation : set) {
            map.put("message", constraintViolation.getMessage());
        }
        return map;
    }
}
