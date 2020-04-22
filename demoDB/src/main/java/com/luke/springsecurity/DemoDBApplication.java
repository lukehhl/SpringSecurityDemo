package com.luke.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

@SpringBootApplication
@MapperScan("com.luke.springsecurity.dao")
public class DemoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDBApplication.class, args);
    }

    //路径通配类
    @Bean
    AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
}
