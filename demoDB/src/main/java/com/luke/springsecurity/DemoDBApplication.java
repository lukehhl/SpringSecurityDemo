package com.luke.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luke.springsecurity.dao")
public class DemoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDBApplication.class, args);
    }

}
