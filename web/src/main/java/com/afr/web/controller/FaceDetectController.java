package com.afr.web.controller;

import com.afr.entrys.businessdomain.DetectFaceRequestParam;
import com.afr.facerecognition.request.DetectFaceRequest;
import com.afr.facerecognition.response.DetectFaceResponse;
import com.afr.service.facedetect.FaceDetectService;
import com.afr.utils.CallResult;
import com.afr.utils.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author Yan liang
 * @create 2019/3/30
 * @since 1.0.0
 */
@Controller
public class FaceDetectController {

    @Autowired
    private FaceDetectService faceDetectService;

    @RequestMapping(value = "/FaceDetect/detectImage", method = RequestMethod.POST)
    @ResponseBody
    public CallResult detectLocalImage(DetectFaceRequestParam param) {
        return faceDetectService.detectImage(param);
    }
}