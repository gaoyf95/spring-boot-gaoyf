package com.gaoyf.jpa.service;

import com.gaoyf.jpa.entity.User;
import com.gaoyf.jpa.repositories.UserJpaRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2018/9/27 17:34:38
 */
@Service
public class UserJpaService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    public List<User> getList() {
        return IterableUtils.toList(userJpaRepository.findAll());
    }

    public User getUserById(String id) {
        return userJpaRepository.findById(id).get();
    }

    public User save(User user) {
        return userJpaRepository.save(user);
    }

    public void delete(String id) {
        userJpaRepository.deleteById(id);
    }
}
