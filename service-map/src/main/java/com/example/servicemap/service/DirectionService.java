package com.example.servicemap.service;

import com.example.internalcommon.constant.MapStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.DirectionResponse;
import com.example.servicemap.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: DirectionService
 * Package: com.example.servicemap.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 19:58
 * @Version 1.0
 */
@Service
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    /**
     * 根据起点经纬度和终点经纬度获取距离（米）和时长（秒）
     * @param depLongitude 起点经度
     * @param depLatitude 起点纬度
     * @param destLongitude 终点经度
     * @param destLatitude  终点纬度
     * @return
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        // 调用第三方地图服务
        DirectionResponse directionResponse = mapDirectionClient.dircetionByDriviing(depLongitude, depLatitude, destLongitude, destLatitude);

        return ResponseResult.success(MapStatusEnum.ROUTING_SUCCESS, directionResponse);
    }
}
