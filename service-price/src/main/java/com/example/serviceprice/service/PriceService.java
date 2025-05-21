package com.example.serviceprice.service;

import com.example.internalcommon.constant.PriceStatusEnum;
import com.example.internalcommon.dto.PriceRule;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.PriceDto;
import com.example.internalcommon.response.DirectionResponse;
import com.example.internalcommon.response.ForecastPriceResponse;
import com.example.serviceprice.mapper.PriceRuleMapper;
import com.example.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: PriceService
 * Package: com.example.serviceprice.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 19:22
 * @Version 1.0
 */
@Service
@Slf4j
public class PriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult forecastPrice(@RequestBody PriceDto priceDto){

//        String depLongitude = priceDto.getDepLongitude();
//        String depLatitude = priceDto.getDepLatitude();
//        String destLongitude = priceDto.getDestLongitude();
//        String destLatitude = priceDto.getDestLatitude();
//
//        log.info("出发地经度：" + depLongitude);
//        log.info("出发地纬度：" + depLatitude);
//        log.info("目的地经度：" + destLongitude);
//        log.info("目的地纬度：" + destLatitude);


        log.info("调用地图服务，查询距离和价格");
        ResponseResult<DirectionResponse> driving = serviceMapClient.driving(priceDto);
        Integer distance = driving.getData().getDistance();
        Integer duration = driving.getData().getDuration();

        log.info("读取计价规则");
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code", "11000");
        queryMap.put("vehicle_type", "1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);

        log.info("根据距离、时长、计价规则，计算价格");

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(21.35);

        return ResponseResult.success(PriceStatusEnum.PRICE_CALCULATED, forecastPriceResponse);
    }
}
