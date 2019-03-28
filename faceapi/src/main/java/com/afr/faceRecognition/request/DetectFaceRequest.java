package com.afr.faceRecognition.request;

import com.afr.faceRecognition.base.BaseFaceRequest;
import com.afr.faceRecognition.response.DetectFaceResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan liang
 * @create 2019/3/28
 * @since 1.0.0
 */
public class DetectFaceRequest extends BaseFaceRequest<DetectFaceResponse>{
    // api地址
    private static String apiUrl = "https://api-cn.faceplusplus.com/facepp/v3/detect";

    @Override
    protected String getApiUrl() {
        return apiUrl;
    }

    @Override
    protected Map<String, String> addApiParams() {
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("image_url", "https://cdn.faceplusplus.com.cn/mc-official/scripts/demoScript/images/demo-pic6.jpg");
        return paramsMap;
    }

    @Override
    protected Map<String, Byte[]> addApiByteParams() {
        Map<String, Byte[]> byteMap = new HashMap<>();

        return byteMap;
    }

    @Override
    protected Class<DetectFaceResponse> getResponseClass() {
        return DetectFaceResponse.class;
    }
}