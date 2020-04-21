package com.luke.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Demo01Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo01Application.class, args);
    }

}
