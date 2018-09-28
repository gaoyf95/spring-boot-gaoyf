package com.gaoyf.resultful.repositories;

import com.gaoyf.resultful.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 高宇飞 on 2018/9/27 17:21:33
 */
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getList() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserById(Integer id) {
        String sql = "select * from user where  id = ?";
        List<User> query = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        return query.size() > 0 ? query.get(0) : new User();
    }

    public User save(User user) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "insert into user (name) values (:name)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rc = namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
        if (rc > 0) {
            user.setId(keyHolder.getKey().intValue());
            return user;
        } else {
            return new User();
        }
    }
}
