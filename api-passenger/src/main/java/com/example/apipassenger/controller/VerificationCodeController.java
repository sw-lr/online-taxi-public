package com.example.apipassenger.controller;

import com.example.apipassenger.request.VerificationCodeDTO;
import com.example.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: VerificationCodeController
 * Package: com.example.apipassenger.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/25 16:12
 * @Version 1.0
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        // 获取验证码
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的手机参数为"+passengerPhone);

        return verificationCodeService.generatorCode(passengerPhone);
    }
}
