package com.example.internalcommon.core;

import java.text.MessageFormat;

/**
 * ClassName: StatusCode
 * Package: com.example.internalcommon.core
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/26 20:23
 * @Version 1.0
 */
public interface StatusCode {
    int getCode();
    String getMessage();

    /**
     * 动态消息模板（带参数）
     * @param args 动态参数
     * @return 格式化后的消息
     */
    default String formatMessage(Object... args){
        return MessageFormat.format(getMessage(), args);
    }
}
