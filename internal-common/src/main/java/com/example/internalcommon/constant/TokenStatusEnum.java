package com.example.internalcommon.constant;

import com.example.internalcommon.core.StatusCode;

/**
 * ClassName: ToeknStatusEnum
 * Package: com.example.internalcommon.constant
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/16 9:44
 * @Version 1.0
 */
public enum TokenStatusEnum implements StatusCode {

    // 双token生成成功
    AUTH_SUCCESS(200, "身份验证成功"),
    TOKEN_REFRESHED(200, "令牌已刷新"),

    // 通用token错误
    TOKEN_INVALID(9401, "Token无效"),
    TOKEN_EXPIRED(9402, "Token已过期"),
    TOKEN_FORMAT_ERROR(9403, "Token格式错误")
    ;

    private final int code;

    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    TokenStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
