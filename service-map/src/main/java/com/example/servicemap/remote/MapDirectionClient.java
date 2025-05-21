package com.example.servicemap.remote;

import com.example.internalcommon.constant.AmapConfigConstants;
import com.example.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName: MapDirectionClient
 * Package: com.example.servicemap.remote
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 21:02
 * @Version 1.0
 */
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.api-key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse dircetionByDriviing(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        // 组装url
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(AmapConfigConstants.DIRECTION_URL);
        urlBuilder.append("?");
        urlBuilder.append("origin=" + depLongitude + "," + depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination=" + destLongitude + "," + destLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extensions=all");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key=" + apiKey);

        // 调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = directionEntity.getBody();

        // 解析接口
        return parseDirectionEntity(directionString);

    }

    private DirectionResponse parseDirectionEntity(String directionString){

        DirectionResponse directionResponse = null;

        try {
            JSONObject directionObject = JSONObject.fromObject(directionString);
            if (directionObject.has(AmapConfigConstants.STATUS)){
                int status = directionObject.getInt(AmapConfigConstants.STATUS);
                if (status == 1){
                    if (directionObject.has(AmapConfigConstants.ROUTE)){
                        JSONObject routeObject = directionObject.getJSONObject(AmapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                        JSONObject pathObject = pathsArray.getJSONObject(0);
                        directionResponse = new DirectionResponse();
                        if (pathObject.has(AmapConfigConstants.DISTANCE)){
                            int distance = pathObject.getInt(AmapConfigConstants.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if (pathObject.has(AmapConfigConstants.DURATION)){
                            int duration = pathObject.getInt(AmapConfigConstants.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }
        } catch(Exception e){

        }

        return directionResponse;
    }
}
