package com.example.internalcommon.constant;

import com.example.internalcommon.core.StatusCode;

/**
 * ClassName: PriceStatusEnum
 * Package: com.example.internalcommon.constant
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 15:48
 * @Version 1.0
 */
public enum PriceStatusEnum implements StatusCode {

    /**
     * 预估价格计算成功
     */
    PRICE_CALCULATED(200, "预估价格已生成"),

    /**
     * 计价规则不存在
     */
    RULE_NOT_FOUND(9403, "未匹配到计价规则（城市码：{0}，车型：{1}）")
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

    PriceStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
