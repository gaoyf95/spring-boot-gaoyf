package com.gaoyf.jpa.service;

import com.gaoyf.jpa.entity.SystemLog;
import com.gaoyf.jpa.repositories.SystemLogRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 高宇飞 on 2018/9/27 17:34:38
 */
@Service
public class SystemLogService {

    @Autowired
    private SystemLogRepository systemLogRepository;

    public List<SystemLog> getList() {
        return IterableUtils.toList(systemLogRepository.findAll());
    }

    public SystemLog getUserById(String id) {
        return systemLogRepository.findById(id).get();
    }

    @Async
    public SystemLog save(SystemLog user) {
        return systemLogRepository.save(user);
    }

    public void delete(String id) {
        systemLogRepository.deleteById(id);
    }
}
