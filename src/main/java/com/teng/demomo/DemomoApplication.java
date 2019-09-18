package com.teng.demomo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.teng.demomo.dao")
@SpringBootApplication
public class DemomoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomoApplication.class, args);
    }

}
