package com.gaoyf.resultful.controller;

import com.gaoyf.resultful.model.User;
import com.gaoyf.resultful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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


    /**
     * URL中携带占位符
     * //@PathVariable 可以用来映射URL中的占位符到目标方法的参数中
     *
     * @param id id
     * @return 用户实体
     */
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    /**
     * 方法参数验证
     *
     * @param id id
     * @return 用户
     */
    @RequestMapping(value = "/getUserById2", method = RequestMethod.GET)
    public User getUserById2(@NotNull(message = "id不能为空") String id) {
        return userService.getUserById(id);
    }

    /**
     * 实体对象校验
     *
     * @param user 对象
     * @return 用户
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(@Valid User user) {
        return userService.save(user);
    }
}
