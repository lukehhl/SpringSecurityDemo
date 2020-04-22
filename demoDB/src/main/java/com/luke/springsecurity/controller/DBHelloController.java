package com.luke.springsecurity.controller;

import com.luke.springsecurity.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@AllArgsConstructor
@RequestMapping("db")
public class DBHelloController {
    private final MenuService menuService;

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

    @GetMapping("test")
    public String dbtest() {
        return "hello test";
    }

    @GetMapping("menu")
    public String menu() {
        return menuService.queryALlWithRoles().toString();
    }
}
