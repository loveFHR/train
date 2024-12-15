package com.fhr.train.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FHR
 * @create 2024/12/15 16:46
 */
@RestController
public class TestController {
    @GetMapping("hello")
    public String hello() {
        return "hello world!";
    }
}
