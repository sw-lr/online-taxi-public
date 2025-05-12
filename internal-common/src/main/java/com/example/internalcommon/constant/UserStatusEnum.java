package com.example.internalcommon.constant;

import com.example.internalcommon.core.StatusCode;

/**
 * ClassName: UserStatusEnum
 * Package: com.example.internalcommon.constant
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/12 16:13
 * @Version 1.0
 */
public enum UserStatusEnum implements StatusCode {
    // 成功状态码
    USER_CREATED(201, "用户注册成功"),
    LOGIN_SUCCESS(200, "登录成功")
    ;

    private final int code;

    private final String message;

    UserStatusEnum(int code, String message) {
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
