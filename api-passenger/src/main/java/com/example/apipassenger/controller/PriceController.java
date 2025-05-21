package com.example.apipassenger.controller;

import com.example.apipassenger.service.PriceService;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.PriceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: PriceController
 * Package: com.example.apipassenger.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 15:37
 * @Version 1.0
 */
@RestController
@Slf4j
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody PriceDto priceDto){

        return priceService.forecastPrice(priceDto);
    }
}
