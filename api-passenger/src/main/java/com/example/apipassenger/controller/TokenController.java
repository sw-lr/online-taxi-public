package com.example.apipassenger.controller;

import com.example.apipassenger.service.TokenService;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TokenController
 * Package: com.example.apipassenger.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/16 9:18
 * @Version 1.0
 */
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshTokenSrc = tokenResponse.getRefreshToken();

        return tokenService.refreshToken(refreshTokenSrc);
    }
}
