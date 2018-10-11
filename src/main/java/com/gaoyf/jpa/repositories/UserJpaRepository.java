package com.gaoyf.jpa.repositories;

import com.gaoyf.jpa.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 高宇飞 on 2018/9/27 17:21:33
 */
@Repository
public interface UserJpaRepository extends CrudRepository<User, String> {

    User findByName(String name);

}
