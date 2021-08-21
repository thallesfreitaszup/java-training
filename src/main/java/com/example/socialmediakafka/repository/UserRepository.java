package com.example.socialmediakafka.repository;

import com.example.socialmediakafka.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository  {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> create(User user){
        this.jdbcTemplate.update(
                "insert into users(id,name,age,city,email,password) values(?,?,?,?,?,?)",
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getCity(),
                user.getEmail(),
                user.getPassword()
        );
        return this.findById(user.getId());
    }

    public Optional<User> findById(String id) {
        return jdbcTemplate.queryForObject(
                "select * from users where id = ?",
                (rs, rowNum) ->
                        Optional.of(new User(
                                rs.getString("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("city"),
                                rs.getString("email"),
                                rs.getString("password")
                        )), id
        );
    }

}
