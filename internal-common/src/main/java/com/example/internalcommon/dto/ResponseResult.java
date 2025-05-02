package com.example.internalcommon.dto;

import com.example.internalcommon.core.StatusCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * ClassName: ResponseResult
 * Package: com.example.internalcommon.dto
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/26 17:29
 * @Version 1.0
 */
public class ResponseResult<T> implements Serializable {

    private final int code;
    private final String message;
    private final T data;

    /**
     * 自定义成功模板（动态消息）
     * @param status 状态码
     * @param messageArgs 消息
     * @param data 数据
     * @return
     * @param <T>
     */
    public static <T> ResponseResult<T> success(StatusCode status, T data, Object... messageArgs){
        return new ResponseResult<>(status, data, messageArgs);
    }

    /**
     * 自定义成功模板（无数据）
     * @param status  状态枚举类
     * @return
     */
    public static ResponseResult success(StatusCode status){
        return new ResponseResult<>(status, null);
    }

    /**
     * 自定义成功模板（带数据）
     * @param status 状态枚举类
     * @param data 数据
     * @return
     * @param <T>
     */
    public static <T> ResponseResult<T> success(StatusCode status, T data){
        return new ResponseResult<>(status, data);
    }

    /**
     * 自定义失败模板（动态消息）
     * @param errorStatus 状态枚举类
     * @param messageArgs 消息
     * @param data 具体错误信息
     * @return
     * @param <T>
     */
    public static <T> ResponseResult<T> fail(StatusCode errorStatus, T data, Object... messageArgs){
        return new ResponseResult<>(errorStatus, data, messageArgs);
    }

    /**
     * 自定义失败模板（无具体错误信息）
     * @param errorStatus 状态枚举
     * @return
     */
    public static ResponseResult fail(StatusCode errorStatus){
        return new ResponseResult<>(errorStatus, null);
    }

    /**
     * 自定义失败模板（有具体错误信息）
     * @param errorStatus 状态枚举
     * @return
     */
    public static <T> ResponseResult<T> fail(StatusCode errorStatus, T data){
        return new ResponseResult<>(errorStatus, data);
    }

    /**
     * 业务构造器
     * @param status
     * @param data
     * @param messageArgs
     */
    private ResponseResult(StatusCode status, T data, Object... messageArgs) {
        this.code = status.getCode();
        this.message = messageArgs.length > 0 ?
                status.formatMessage(messageArgs) : // 有参数时格式化
                status.getMessage(); // 无参数时直接使用原始消息
        this.data = data;
    }

    /**
     * Jackson 反序列化专用构造器
     * @param code
     * @param message
     * @param data
     */
    @JsonCreator
    public ResponseResult(
            @JsonProperty("code") int code,
            @JsonProperty("message") String message,
            @JsonProperty("data") T data
    ){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
