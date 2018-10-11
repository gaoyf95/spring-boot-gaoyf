package com.gaoyf.resultful.service;

import com.gaoyf.resultful.model.User;
import com.gaoyf.resultful.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2018/9/27 17:34:38
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getList(){
        return userRepository.getList();
    }

    public User getUserById(String id){
        return userRepository.getUserById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
