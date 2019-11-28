package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages="com.example.dao")
@SpringBootApplication
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
@EnableTransactionManagement
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

}
