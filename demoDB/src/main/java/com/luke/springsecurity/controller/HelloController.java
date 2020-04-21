package com.luke.springsecurity.controller;

import com.luke.springsecurity.service.MethodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {
    private final MethodService methodService;

    @GetMapping("hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("user/hello")
    public String user() {
        return "hello user";
    }

    @GetMapping("login")
    public String login() {
        return "please login";
    }

    @GetMapping("hello1")
    public String hello1() {
        return methodService.admin();
    }

    @GetMapping("hello2")
    public String hello2() {
        return methodService.user();
    }

    @GetMapping("hello3")
    public String hello3() {
        return methodService.hello();
    }
}
