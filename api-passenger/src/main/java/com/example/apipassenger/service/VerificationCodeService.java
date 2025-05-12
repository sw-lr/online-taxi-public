package com.example.apipassenger.service;

import com.example.apipassenger.remote.ServiceVerificationcodeClient;
import com.example.internalcommon.constant.PassengerStatusEnum;
import com.example.internalcommon.constant.VerificationcodeStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.NumberCodeResponse;
import com.example.internalcommon.response.TokenResponse;
import org.apache.commons.lang.StringUtils;
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
        String key = generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode, 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上。阿里短信服务，腾讯短信服务，华信，容联

        return ResponseResult.success(PassengerStatusEnum.SUCCESS);
    }

    /**
     * 根据手机号生成key
     * @param passengerPhone 乘客手机号
     * @return Redis中的key
     */
    private String generatorKeyByPhone(String passengerPhone){
        return verificationcodePrefix + passengerPhone;
    }

    /**
     * 校验验证码
     * @param passengerPhone
     * @param verificationCode
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode){
        // 根据手机号从redis获取验证码
        String key = generatorKeyByPhone(passengerPhone);
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis的验证码：" + redisCode);

        // 校验验证码
        System.out.println("校验验证码");
        if (StringUtils.isBlank(redisCode)){
            return ResponseResult.fail(VerificationcodeStatusEnum.CODE_NOT_FOUND);
        }
        if (!redisCode.equals(verificationCode.trim())){
            return ResponseResult.fail(VerificationcodeStatusEnum.CODE_MISMATCH);
        }

        // 判断该用户是否已经注册
        System.out.println("判断该用户是否已经注册");

        // 颁发令牌
        System.out.println("颁发令牌");

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token");
        return ResponseResult.success(VerificationcodeStatusEnum.LOGIN_SUCCESS, tokenResponse);
    }
}
