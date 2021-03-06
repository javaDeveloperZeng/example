package com.example.service;

import com.example.dao.UserMapper;
import com.example.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceThree {
    @Autowired
    UserMapper userMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserServiceTwo userServiceTwo;
    public void selectUser(){

        userServiceTwo.selectUser();
    }
  @Transactional
    public void test1(){
        //子1
        insetData();
      //子2
        userServiceTwo.updateData();
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void insertUser(){
        List<Map<String,Object>> result=jdbcTemplate.queryForList("select * from user where id='1'");
        if(result.isEmpty()){
            System.out.println("result无值");
        }else {
            System.out.println(result.get(0).get("id"));
        }

        jdbcTemplate.execute("insert user (id,userName,passWord,realName) " +
                "values ('1','a','b','c')");
//        userServiceTwo.updateUser();
        List<Map<String,Object>> result2=jdbcTemplate.queryForList("select * from user where id='1'");
        if(result2.isEmpty()){
            System.out.println("result无值");
        }else {
            System.out.println(result2.get(0).get("id"));
        }
    }
    @Transactional
    public void insetData(){
        jdbcTemplate.execute("insert user (id,userName,passWord,realName) " +
                "values ('1','a','b','c')");
        System.out.println("子1执行完");
    }
    public User findUserById(int id){
        return userMapper.findUserById(id);
    }
    public User findUserByName(String name){
        return userMapper.findUserByName(name);
    }


    void insertUser(User user){ userMapper.insertUser(user);};


    @Transactional
    public void insetData2(){
        jdbcTemplate.execute("insert user (id,userName,passWord,realName) " +
                "values ('2','abc','b','c')");
        System.out.println("孙1inset执行完");
       int i=1/0;
    }
}
