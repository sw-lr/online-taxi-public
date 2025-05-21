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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        String cityCode = "11000";
        String vehicleType = "1";
        queryMap.put("city_code", cityCode);
        queryMap.put("vehicle_type", vehicleType);
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);

        if (priceRules.size() == 0){
            return ResponseResult.fail(PriceStatusEnum.RULE_NOT_FOUND, null, cityCode, vehicleType);
        }
        PriceRule priceRule = priceRules.get(0);

        log.info("根据距离、时长、计价规则，计算价格");
        double price = getPrice(distance, duration, priceRule);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);

        return ResponseResult.success(PriceStatusEnum.PRICE_CALCULATED, forecastPriceResponse);
    }

    /**
     * 根据距离、时长和计价规则，计算最终价格
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return
     */
    private static double getPrice(Integer distance, Integer duration, PriceRule priceRule){
        BigDecimal price = new BigDecimal(0);

        // 起步价
        Double startFare = priceRule.getStartFare();
        BigDecimal startFareDecimal = new BigDecimal(startFare);
        price = price.add(startFareDecimal);

        // 里程价
        // 总里程 m
        BigDecimal distanceDecimal = new BigDecimal(distance);
        // 总里程 km
        BigDecimal distanceMileDecimal = distanceDecimal.divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP);
        // 起步里程
        Integer startMile = priceRule.getStartMile();
        BigDecimal startMileDecimal = new BigDecimal(startMile);
        // 最终收费得里程数 km
        double mileSubtractDecimal = distanceMileDecimal.subtract(startMileDecimal).doubleValue();
        Double mile = mileSubtractDecimal > 0 ? mileSubtractDecimal : 0;
        BigDecimal mileDecimal = new BigDecimal(mile);
        // 计程单价 元/km
        Double unitPricePerMile = priceRule.getUnitPricePerMile();
        BigDecimal unitPricePerMileDecimal = new BigDecimal(unitPricePerMile);
        // 里程价格
        BigDecimal mileFare = mileDecimal.multiply(unitPricePerMileDecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        price = price.add(mileFare);

        // 时长价
        // 总时长 秒
        BigDecimal durationSecondDecimal = new BigDecimal(duration);
        // 总时长 分钟
        BigDecimal durationMinutesDecimal = durationSecondDecimal.divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);
        // 计时单价
        Double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        BigDecimal unitPricePerMinuteDecimal = new BigDecimal(unitPricePerMinute);
        BigDecimal durationFare = durationMinutesDecimal.multiply(unitPricePerMinuteDecimal);
        price = price.add(durationFare).setScale(2, BigDecimal.ROUND_HALF_UP);

        return price.doubleValue();
    }
}
