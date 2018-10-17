package com.gaoyf.resultful.service;

import com.gaoyf.resultful.dao.UserDao;
import com.gaoyf.resultful.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2018/9/27 17:34:38
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getList(){
        return userDao.getList();
    }

    public User getUserById(String id){
        return userDao.getUserById(id);
    }

    public User save(User user) {
        return userDao.save(user);
    }
}
