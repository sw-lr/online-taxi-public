package com.example.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.internalcommon.constant.IdentityConstant;
import com.example.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
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

    /**
     * 生成token
     * @param passengerPhone
     * @return
     */
    public static String generatorToken(String passengerPhone, String identity){
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);

        // 过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();

        // 整合map
        map.forEach(
                (k, v) -> {
                    builder.withClaim(k, v);
                }
        );

        // 添加过期时间
        builder.withExpiresAt(date);

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
        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();

        TokenResult tokenResult = new TokenResult(phone, identity);

        return tokenResult;
    }

    public static void main(String[] args) {
        String s = generatorToken("15627582811", IdentityConstant.PASSENGER_IDENTITY);
        System.out.println(s);

        System.out.println(parseToken(s));
    }

}
