package com.example.apipassenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * ClassName: VerificationCodeService
 * Package: com.example.apipassenger.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/25 16:16
 * @Version 1.0
 */
@Service
public class VerificationCodeService {
    public String generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code = "123456";

        // 存入redis
        System.out.println("存入redis");

        // 返回值
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("message","验证码发送成功");

        return jsonObject.toString();
    }
}
