package com.example.apipassenger.service;

import com.example.apipassenger.remote.ServicePassengerUserClient;
import com.example.apipassenger.remote.ServiceVerificationcodeClient;
import com.example.internalcommon.constant.IdentityConstants;
import com.example.internalcommon.constant.PassengerStatusEnum;
import com.example.internalcommon.constant.TokenConstatnts;
import com.example.internalcommon.constant.VerificationcodeStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import com.example.internalcommon.response.NumberCodeResponse;
import com.example.internalcommon.response.TokenResponse;
import com.example.internalcommon.util.JwtUtils;
import com.example.internalcommon.util.RedisPrefixUtils;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult generatorCode(String passengerPhone){
        // 调用验证码服务，获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        String numberCode = numberCodeResponse.getData().getNumberCode();

        // 存入redis
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode, 2, TimeUnit.MINUTES);

        // 通过短信服务商，将对应的验证码发送到手机上。阿里短信服务，腾讯短信服务，华信，容联

        return ResponseResult.success(PassengerStatusEnum.SUCCESS);
    }

    /**
     * 校验验证码
     * @param passengerPhone
     * @param verificationCode
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode){
        // 根据手机号从redis获取验证码
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone);
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
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        // 颁发令牌
        String assessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstatnts.ACCESS_TOKEN);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstatnts.REFRESH_TOKEN);


        String assessTokenKey = RedisPrefixUtils.generatorTokenByKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstatnts.ACCESS_TOKEN);
        String refreshTokenKey = RedisPrefixUtils.generatorTokenByKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstatnts.REFRESH_TOKEN);

        stringRedisTemplate.opsForValue().set(assessTokenKey, assessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);


        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(assessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(VerificationcodeStatusEnum.LOGIN_SUCCESS, tokenResponse);
    }
}
