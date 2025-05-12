package com.example.internalcommon.constant;

import com.example.internalcommon.core.StatusCode;

/**
 * ClassName: VerificationcodeStatusEnum
 * Package: com.example.internalcommon.constant
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/26 21:29
 * @Version 1.0
 */
public enum VerificationcodeStatusEnum implements StatusCode {

    // 成功状态码
    CODE_GENERATED(200, "验证码生成成功"),
    LOGIN_SUCCESS(200, "登录成功")
    ;

    private int code;
    private String message;

    VerificationcodeStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
