package com.example.servicemap.controller;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.PriceDto;
import com.example.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: DirectionController
 * Package: com.example.servicemap.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 19:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/direction")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @GetMapping("/driving")
    public ResponseResult driving(@RequestBody PriceDto priceDto){

        String depLongitude = priceDto.getDepLongitude();
        String depLatitude = priceDto.getDepLatitude();
        String destLongitude = priceDto.getDestLongitude();
        String destLatitude = priceDto.getDestLatitude();

        return directionService.driving(depLongitude, depLatitude, destLongitude, destLatitude);
    }
}
