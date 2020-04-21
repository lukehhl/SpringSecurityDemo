package com.luke.springsecurity.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("db")
public class DBHelloController {

    @GetMapping("admin/hello")
    public String admin() {
        return "hello db admin";
    }

    @GetMapping("user/hello")
    public String user() {
        return "hello db user";
    }

    @GetMapping("dba/hello")
    public String dba() {
        return "hello db dba";
    }
}
