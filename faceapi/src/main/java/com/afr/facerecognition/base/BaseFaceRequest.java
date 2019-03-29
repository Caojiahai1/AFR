package com.afr.facerecognition.base;

import com.afr.utils.jsonhelper.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan liang
 * @create 2019/3/28
 * @since 1.0.0
 */
public abstract class BaseFaceRequest<T extends BaseFaceResponse> {
    // 授权信息
    private static String apiKey = "fPeAHhwF7wt5mjPLOILN16SuRh1qXpyD";
    private static String apiSecret = "y2fAeFrg6DMyALDCiAtjX-XSQY6Zkz3e";

    // Api地址
    protected abstract String getApiUrl();

    // 获取接口参数map
    protected abstract Map<String, String> addApiParams() throws Exception;
    protected abstract Map<String, byte[]> addApiByteParams();
    // 获取返回类型
    protected abstract Class<T> getResponseClass();

    // string请求参数
    private Map<String, String> paramsMap = new HashMap<>();
    // byte请求参数
    private Map<String, byte[]> byteMap = new HashMap<>();

    // 请求
    private String getResponseString () throws Exception{
        paramsMap.put("api_key", apiKey);
        paramsMap.put("api_secret", apiSecret);
        paramsMap.putAll(addApiParams());
        byteMap = addApiByteParams();
        byte[] bytes = PostHelper.post(getApiUrl(), paramsMap, byteMap);
        return new String(bytes);
    }

    /**
     *@description
     *@params  []
     *@return  T
     *@creater  yanliang
     *@createdate  2019/3/29
     *@info
     */
    public T getResponse () {
        T response = null;
        try {
            String json = getResponseString();
            response = JsonUtils.readObject(json, getResponseClass());
        } catch (Exception e) {
            try {
                response = getResponseClass().newInstance();
                response.setErrorMessage("系统错误");
            } catch (InstantiationException e1) {

            } catch (IllegalAccessException e1) {

            }
        }
        return response;
    }
}