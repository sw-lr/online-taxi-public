package com.example.internalcommon.util;

/**
 * ClassName: RedisPrefixUtils
 * Package: com.example.internalcommon.util
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/15 15:06
 * @Version 1.0
 */
public class RedisPrefixUtils {

    // 乘客验证码的前缀
    public static String verificationcodePrefix = "passenger-verification-code-";

    // token前缀
    public static String tokenPreifx = "token-";


    /**
     * 根据手机号生成key
     * @param passengerPhone 乘客手机号
     * @return Redis中的key
     */
    public static String generatorKeyByPhone(String passengerPhone){
        return verificationcodePrefix + passengerPhone;
    }

    /**
     * 生成token前缀
     * @param phone 手机号
     * @param identity 身份
     * @return
     */
    public static String generatorTokenByKey(String phone, String identity, String tokenType){
        return tokenPreifx + phone + "-" + identity + "-" + tokenType;
    }
}
