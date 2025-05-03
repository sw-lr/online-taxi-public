package com.example.apipassenger.service;

import com.example.apipassenger.remote.ServiceVerificationcodeClient;
import com.example.internalcommon.constant.PassengerStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    // 乘客验证码的前缀
    private String verificationcodePrefix = "passenger-verification-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    public ResponseResult generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        String numberCode = numberCodeResponse.getData().getNumberCode();

        // 存入redis
        String key = verificationcodePrefix + passengerPhone;
        stringRedisTemplate.opsForValue().set(key, numberCode, 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上。阿里短信服务，腾讯短信服务，华信，容联

        return ResponseResult.success(PassengerStatusEnum.SUCCESS);
    }
}
