package com.gaoyf.jpa.repositories;

import com.gaoyf.jpa.entity.SystemLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 高宇飞 on 2018/10/15 13:57:06
 */
@Repository
public interface SystemLogRepository extends CrudRepository<SystemLog, String> {
}
