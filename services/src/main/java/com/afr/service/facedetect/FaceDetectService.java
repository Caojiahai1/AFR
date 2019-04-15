package com.afr.service.facedetect;

import com.afr.entrys.businessdomain.DetectFaceRequestParam;
import com.afr.facerecognition.request.DetectFaceRequest;
import com.afr.facerecognition.response.DetectFaceResponse;
import com.afr.memcache.MySpyMemcache;
import com.afr.utils.CallResult;
import com.afr.utils.MyConstant;
import com.afr.utils.MyLogger;
import com.afr.utils.MyMD5Help;
import com.afr.utils.jsonhelper.JsonUtils;
import com.afr.utils.stringhelper.StringHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author Yan liang
 * @create 2019/3/30
 * @since 1.0.0
 */
@Service
public class FaceDetectService {

    @Autowired
    private MySpyMemcache mySpyMemcache;

    /**
     *@description 调用detect接口
     *@params  [param]
     *@return  com.afr.utils.CallResult
     *@creater  yanliang
     *@createdate  2019/4/3
     *@info
     */
    public CallResult detectImage(DetectFaceRequestParam param) {
        CallResult callResult = new CallResult();
//        String key = param.toString().length() >= 250 ? param.toString().substring(0,249) : param.toString();
        //MD5生成key
        String key = MyMD5Help.getMD5(param.toString());
        String responseJson = (String) mySpyMemcache.get(key);
        DetectFaceResponse response = null;
        if (responseJson != null && !StringHelper.IsNullOrEmpty(responseJson)) {
            try {
                response = JsonUtils.readObject(responseJson, DetectFaceResponse.class);
            } catch (IOException e) {
                MyLogger.logger.error("从缓存读取key:" + key + "异常:" + e.getMessage());
            }
        }
        if (response != null && response.IsSuccess()) {
            callResult.setSuccess(true);
            callResult.setObject(response.getFaces());
        } else {
            DetectFaceRequest request = new DetectFaceRequest();
            if (!StringHelper.IsNullOrEmpty(param.getLocalPath())) {
                File file = new File(param.getLocalPath());
                if (file == null) {
                    callResult.setSuccess(false);
                    callResult.setMessage(MyConstant.File_EMPTY_ERROR);
                    return callResult;
                }
                request.setImageFile(file);
            }
            if (!StringHelper.IsNullOrEmpty(param.getNetUrl())) {
                request.setImageUrl(param.getNetUrl());
            }
            request.setReturnAttributes("gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
            response = request.getResponse();
            if (response.IsSuccess()) {
                callResult.setSuccess(true);
                callResult.setObject(response.getFaces());
                try {
                    mySpyMemcache.set(key, JsonUtils.writeValueAsString(response));
                } catch (JsonProcessingException e) {
                    MyLogger.logger.error("key:" + key + "加入缓存异常:" + e.getMessage());
                }
            } else {
                callResult.setSuccess(false);
                callResult.setMessage(response.getErrorMessage());
            }
        }
        return callResult;
    }
}