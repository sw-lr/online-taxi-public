package com.example.apipassenger.controller;

import com.example.apipassenger.service.UserService;
import com.example.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: UserController
 * Package: com.example.apipassenger.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/16 15:08
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseResult getUser(HttpServletRequest request){

        // 从请求头中获取accessToken
        String accessToken = request.getHeader("Authorization");

        return userService.getUserByAccessToken(accessToken);
    }
}
