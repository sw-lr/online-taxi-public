package com.example.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.internalcommon.constant.CommonStatusEnum;
import com.example.internalcommon.constant.TokenConstatnts;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResult;
import com.example.internalcommon.util.JwtUtils;
import com.example.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * ClassName: JwtInterceptor
 * Package: com.example.apipassenger.interceptor
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/15 10:17
 * @Version 1.0
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;

        String token = request.getHeader("Authorization");

        TokenResult tokenResult = JwtUtils.checkToken(token);

        if (tokenResult == null){
            result = false;
        }else{
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();

            String key = RedisPrefixUtils.generatorTokenByKey(phone, identity, TokenConstatnts.ACCESS_TOKEN);

            String redisToken = stringRedisTemplate.opsForValue().get(key);

            if (StringUtils.isBlank(redisToken) || !redisToken.equals(token.trim())){
                result = false;
            }
        }

        if (!result){
            response.setCharacterEncoding("UTF-8"); // 添加编码设置
            response.setContentType("application/json;charset=UTF-8"); // 明确内容类型
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(CommonStatusEnum.UNAUTHORIZED)));
        }

        return result;
    }
}
