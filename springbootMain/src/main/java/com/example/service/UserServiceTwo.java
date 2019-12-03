package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@Slf4j
public class UserServiceTwo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public void updateUser(){
        jdbcTemplate.update("update user set userName='ww' where id='1'");
    }
    @Transactional(isolation=Isolation.READ_UNCOMMITTED)
    public void selectUser(){
        List<Map<String,Object>> result=jdbcTemplate.queryForList("select * from user where id='1'");
        if(result.isEmpty()){
            System.out.println("result无值");
        }else {
            System.out.println("读出来的数据"+result.get(0).get("id"));
        }
    }

}
