package com.example.internalcommon.constant;

import com.example.internalcommon.core.StatusCode;

/**
 * ClassName: CommonStatusEnum
 * Package: com.example.internalcommon.constant
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/26 16:26
 * @Version 1.0
 */
public enum CommonStatusEnum implements StatusCode {

    /**
     * 成功状态码
     */
    SUCCESS(200,"操作成功"),
    CREATED(201,"资源创建成功"),

    /**
     * 通用错误码（4xx）
     */
    PARAM_INVALID(400,"请求参数无效"),
    UNAUTHORIZED(401,"身份验证失败"),
    FORBIDDEN(403,"权限不足"),
    NOT_FOUND(404,"资源不存在"),

    /**
     * 系统错误码（5xx）
     */
    INTERNAL_ERROR(500,"系统内部错误"),
    SERVICE_UNAVAILABLE(503,"服务不可用"),
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

    CommonStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
