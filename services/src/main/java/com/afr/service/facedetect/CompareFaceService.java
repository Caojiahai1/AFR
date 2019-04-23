package com.afr.service.facedetect;

import com.afr.entrys.businessdomain.CompareFaceRequestParam;
import com.afr.facerecognition.request.CompareFaceRequest;
import com.afr.facerecognition.response.CompareFaceResponse;
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
import java.util.Date;

/**
 * @author Yan liang
 * @create 2019/4/9
 * @since 1.0.0
 */
@Service
public class CompareFaceService {

    @Autowired
    private MySpyMemcache mySpyMemcache;

    /**
     *@description 根据传入参数调用人脸比对接口
     *@params  [param]
     *@return  com.afr.utils.CallResult
     *@creater  yanliang
     *@createdate  2019/4/16
     *@info
     */
    public CallResult doCompare(CompareFaceRequestParam param) {
        CallResult callResult = new CallResult();
        //MD5生成key
        String key = MyMD5Help.getMD5(param.toString());
        String responseJson = (String) mySpyMemcache.get(key);
        CompareFaceResponse response = null;
        if (responseJson != null && !StringHelper.IsNullOrEmpty(responseJson)) {
            try {
                response = JsonUtils.readObject(responseJson, CompareFaceResponse.class);
            } catch (IOException e) {
                MyLogger.logger.error("从缓存读取key:" + key + "异常:" + e.getMessage());
            }
        }
        if (response != null && response.IsSuccess()) {
            callResult.setSuccess(true);
            callResult.setObject(response);
        } else {
            CompareFaceRequest request = new CompareFaceRequest();
            if (!StringHelper.IsNullOrEmpty(param.getFaceToken1())) {
                request.setFaceToken1(param.getFaceToken1());
            }
            if (!StringHelper.IsNullOrEmpty(param.getLocalPath1())) {
                File file = new File(param.getLocalPath1());
                if (file == null) {
                    callResult.setSuccess(false);
                    callResult.setMessage(MyConstant.File_EMPTY_ERROR);
                    return callResult;
                }
                request.setImageFile1(file);
            }
            if (!StringHelper.IsNullOrEmpty(param.getNetUrl1())) {
                request.setImageUrl1(param.getNetUrl1());
            }
            if (!StringHelper.IsNullOrEmpty(param.getFaceToken2())) {
                request.setFaceToken2(param.getFaceToken2());
            }
            if (!StringHelper.IsNullOrEmpty(param.getLocalPath2())) {
                File file = new File(param.getLocalPath2());
                if (file == null) {
                    callResult.setSuccess(false);
                    callResult.setMessage(MyConstant.File_EMPTY_ERROR);
                    return callResult;
                }
                request.setImageFile2(file);
            }
            if (!StringHelper.IsNullOrEmpty(param.getNetUrl2())) {
                request.setImageUrl2(param.getNetUrl2());
            }
            response = request.getResponse();
            if (response.IsSuccess()) {
                callResult.setSuccess(true);
                callResult.setObject(response);
                try {
                    mySpyMemcache.set(key, 1800, JsonUtils.writeValueAsString(response));
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