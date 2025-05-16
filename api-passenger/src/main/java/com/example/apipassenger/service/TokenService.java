package com.example.apipassenger.service;

import com.example.internalcommon.constant.TokenStatusEnum;
import com.example.internalcommon.constant.TokenConstatnts;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResult;
import com.example.internalcommon.response.TokenResponse;
import com.example.internalcommon.util.JwtUtils;
import com.example.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: TokenService
 * Package: com.example.apipassenger.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/16 9:20
 * @Version 1.0
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc){
        // 解析refreshToken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null){
            ResponseResult.fail(TokenStatusEnum.TOKEN_INVALID);
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        // 从redis中读取refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenByKey(phone, identity, TokenConstatnts.REFRESH_TOKEN);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        // 校验refreshToken
        if (StringUtils.isBlank(refreshTokenRedis) || !refreshTokenSrc.trim().equals(refreshTokenSrc.trim())){
            return ResponseResult.fail(TokenStatusEnum.TOKEN_INVALID);
        }

        // 生成新的双token
        String accessTokenKey = RedisPrefixUtils.generatorTokenByKey(phone, identity, TokenConstatnts.ACCESS_TOKEN);

        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstatnts.ACCESS_TOKEN);
        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstatnts.REFRESH_TOKEN);

        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 30, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(TokenStatusEnum.TOKEN_REFRESHED, tokenResponse);
    }
}
