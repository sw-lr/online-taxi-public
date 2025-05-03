package com.example.internalcommon.constant;

import com.example.internalcommon.core.StatusCode;

/**
 * ClassName: PassengerStatusEnum
 * Package: com.example.internalcommon.constant
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/26 20:29
 * @Version 1.0
 */
public enum PassengerStatusEnum implements StatusCode {

    /**
     * 乘客验证码发送成功
     */
    SUCCESS(200, "发送验证码成功")
    ;
    private final int code;
    private final String message;

    private PassengerStatusEnum(int code, String message) {
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
