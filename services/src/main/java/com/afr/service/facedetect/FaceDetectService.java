package com.afr.service.facedetect;

import com.afr.entrys.businessdomain.DetectFaceRequestParam;
import com.afr.facerecognition.request.DetectFaceRequest;
import com.afr.facerecognition.response.DetectFaceResponse;
import com.afr.utils.CallResult;
import com.afr.utils.MyConstant;
import com.afr.utils.stringhelper.StringHelper;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Yan liang
 * @create 2019/3/30
 * @since 1.0.0
 */
@Service
public class FaceDetectService {
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
        DetectFaceResponse response = request.getResponse();
        if (response.IsSuccess()) {
            callResult.setSuccess(true);
            callResult.setObject(response.getFaces());
        } else {
            callResult.setSuccess(false);
            callResult.setMessage(response.getErrorMessage());
        }
        return callResult;
    }
}