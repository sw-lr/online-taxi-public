package com.example.internalcommon.constant;

import com.example.internalcommon.core.StatusCode;

/**
 * ClassName: MapStatusEnum
 * Package: com.example.internalcommon.constant
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 20:06
 * @Version 1.0
 */
public enum MapStatusEnum implements StatusCode {
    // 成功状态码（2XX）
    // 路径规划成功
    ROUTING_SUCCESS(200, "路径规划完成")
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

    MapStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
