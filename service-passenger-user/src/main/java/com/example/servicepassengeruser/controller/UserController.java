package com.example.servicepassengeruser.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import com.example.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Package: com.example.servicepassengeruser.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/12 16:04
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();

        System.out.println("手机号：" + passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }
}
