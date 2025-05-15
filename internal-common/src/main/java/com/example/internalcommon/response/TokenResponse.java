package com.example.internalcommon.response;

import lombok.Data;

/**
 * ClassName: TokenResponse
 * Package: com.example.internalcommon.response
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/12 10:55
 * @Version 1.0
 */
@Data
public class TokenResponse {
    private String accessToken;

    private String refreshToken;

}
