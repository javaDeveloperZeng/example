package com.example.dao;

import com.example.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findUserByName(@Param("name") String name);

    User findUserById(int id);

    void insertUser(User user);
}
