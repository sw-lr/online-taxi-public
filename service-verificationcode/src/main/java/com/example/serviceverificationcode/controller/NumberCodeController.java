package com.example.serviceverificationcode.controller;

import com.example.internalcommon.constant.VerificationcodeStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * ClassName: NumberCodeController
 * Package: com.example.serviceverificationcode.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/26 14:33
 * @Version 1.0
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size: " + size);

        if (size <= 0) {
            throw new IllegalArgumentException("验证码长度必须为正整数");
        }

        StringBuilder numberCode = new StringBuilder();
        for (int i = 0;i < size;i++){
            int num = ThreadLocalRandom.current().nextInt(10);
            numberCode.append(num);
        }

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(numberCode.toString());

        System.out.println("验证码为：" + numberCode.toString());

        return ResponseResult.success(VerificationcodeStatusEnum.CODE_GENERATED, response);
    }
}
