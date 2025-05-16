package com.example.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: JwtUtils
 * Package: com.example.internalcommon.util
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/14 21:26
 * @Version 1.0
 */
public class JwtUtils {

    // 盐
    private static final String SIGN = "UTDFrydTFduUUFV!@$%@#%^$@";

    // 手机号
    private static final String JWT_KEY_PHONE = "phone";

    // 身份
    private static final String JWT_KEY_IDENTITY = "identity";

    // token 类型
    private static final String JWT_TOKEN_TYPE = "tokenType";

    // token时间戳，避免token重复
    private static final String JWT_TOKEN_TIME = "tokenTime";

    /**
     * 生成token
     * @param passengerPhone 手机号
     * @param identity 身份
     * @return
     */
    public static String generatorToken(String passengerPhone, String identity, String tokenType){
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());

        JWTCreator.Builder builder = JWT.create();

        // 整合map
        map.forEach(
                (k, v) -> {
                    builder.withClaim(k, v);
                }
        );


        // 生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        TokenResult tokenResult = new TokenResult(phone, identity);

        return tokenResult;
    }

    /**
     * 检查token是否有效
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        }catch (Exception e){

        }
        return tokenResult;
    }

}
