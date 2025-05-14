package com.example.internalcommon.dto;

import lombok.Data;

/**
 * ClassName: TokenResult
 * Package: com.example.internalcommon.dto
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/14 22:03
 * @Version 1.0
 */
@Data
public class TokenResult {
    private String phone;
    private String identity;

    public TokenResult() {
    }

    public TokenResult(String phone, String identity) {
        this.phone = phone;
        this.identity = identity;
    }
}
