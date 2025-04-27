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
public class PassengerStatusEnum implements StatusCode {

    private int code;
    private String message;

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
