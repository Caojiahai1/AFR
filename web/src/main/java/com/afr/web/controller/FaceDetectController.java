package com.afr.web.controller;

import com.afr.entrys.businessdomain.DetectFaceRequestParam;
import com.afr.facerecognition.request.DetectFaceRequest;
import com.afr.facerecognition.response.DetectFaceResponse;
import com.afr.utils.CallResult;
import com.afr.utils.MyConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * @author Yan liang
 * @create 2019/3/30
 * @since 1.0.0
 */
@Controller
public class FaceDetectController {

    @RequestMapping(value = "/FaceDetect/detectLocalImage", method = RequestMethod.POST)
    @ResponseBody
    public CallResult detectLocalImage(DetectFaceRequestParam param) {
        CallResult callResult = new CallResult();
        File file = new File(param.getLocalPath());
        if (file == null) {
            callResult.setSuccess(false);
            callResult.setMessage(MyConstant.File_EMPTY_ERROR);
            return callResult;
        }
        DetectFaceRequest request = new DetectFaceRequest();
        request.setImageFile(file);
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