package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
    @Autowired UserServiceThree userServiceThree;
    public void updateUser(){
        jdbcTemplate.update("update user set userName='ww' where id='1'");
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void test11(){
        List<Map<String,Object>> result=jdbcTemplate.queryForList("select * from user where id='1'");
        if(result.isEmpty()){
            System.out.println("result无值");
        }else {
            System.out.println("-------->"+result.get(0).get("id"));
        }
        try {
            new Thread().sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Map<String,Object>> result1=jdbcTemplate.queryForList("select * from user where id='1'");
        if(result1.isEmpty()){
            System.out.println("result无值");
        }else {
            System.out.println("-------->"+result1.get(0).get("id"));
        }
    }
    @Transactional
    public void selectUser(){
        try{
            int i=1/0;
            List<Map<String,Object>> result=jdbcTemplate.queryForList("select * from user where id='1'");
            if(result.isEmpty()){
                System.out.println("result无值");
            }else {
                System.out.println("读出来的数据"+result.get(0).get("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Transactional
    public void updateData(){
            /*int i=1/0;*/
        jdbcTemplate.execute("update user set userName='222222' where id='1'");

        System.out.println("子2update执行完");
        try{
            userServiceThree.insetData2();
        }catch (Exception e){
           e.printStackTrace();
        }

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insetData2(){
        jdbcTemplate.execute("insert user (id,userName,passWord,realName) " +
                "values ('2','abc','b','c')");
        System.out.println("孙1inset执行完");
        throw new RuntimeException("updateData");
    }

}
