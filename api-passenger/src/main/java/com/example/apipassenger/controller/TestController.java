package com.example.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController
 * Package: com.example.apipassenger.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/24 16:16
 * @Version 1.0
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test api passenger";
    }
}
