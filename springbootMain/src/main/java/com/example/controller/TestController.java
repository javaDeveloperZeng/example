package com.example.controller;

import com.example.service.UserService;
import com.example.service.UserServiceTwo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
//启动Spring
@SpringBootTest

public class TestController {
    Logger logger= LoggerFactory.getLogger(TestController.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserService userService;
    @Autowired
    UserServiceTwo userServiceTwo;

    @Test
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Rollback(false)
    public void testMethod(){
//        userService.insertUser();
      /*  List<Map<String,Object>> result=jdbcTemplate.queryForList("select * from user where id='1'");
        if(result.isEmpty()){
            System.out.println("result无值");
        }else {
            System.out.println(result.get(0).get("id"));
        }*/

        jdbcTemplate.execute("insert user (id,userName,passWord,realName) " +
                "values ('1','a','b','c')");
        System.out.println("<---数据插入-->");
        try {
            new Thread().sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        userServiceTwo.updateUser();
       /* List<Map<String,Object>> result2=jdbcTemplate.queryForList("select * from user where id='1'");
        if(result2.isEmpty()){
            System.out.println("result无值");
        }else {
            System.out.println(result2.get(0).get("id"));
        }*/
        /*userServiceTwo.updateUser();
        logger.debug("执行成功");*/
    }
}
