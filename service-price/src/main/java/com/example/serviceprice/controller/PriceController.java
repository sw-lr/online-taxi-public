package com.example.serviceprice.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.PriceDto;
import com.example.serviceprice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: PriceController
 * Package: com.example.serviceprice.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 19:20
 * @Version 1.0
 */
@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody PriceDto priceDto){

        String depLongitude = priceDto.getDepLongitude();
        String depLatitude = priceDto.getDepLatitude();
        String destLongitude = priceDto.getDestLongitude();
        String destLatitude = priceDto.getDestLatitude();

        return priceService.forecastPrice(depLongitude, depLatitude, destLongitude, destLatitude);
    }
}
