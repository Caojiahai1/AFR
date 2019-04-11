package com.afr.web.controller;

import com.afr.entrys.businessdomain.DetectFaceRequestParam;
import com.afr.service.facedetect.FaceDetectService;
import com.afr.utils.CallResult;
import com.afr.utils.GlobalConfig;
import com.afr.utils.MyConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan liang
 * @create 2019/4/1
 * @since 1.0.0
 */
@Controller
public class ImageUploadController {

    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private FaceDetectService faceDetectService;

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    @ResponseBody
    public CallResult upload(MultipartFile lefile, @RequestParam("json") String json, HttpServletRequest request, HttpServletResponse response) {
        CallResult callResult = new CallResult();
        callResult.setSuccess(true);
        if (lefile.isEmpty()) {
            callResult.setMessage(MyConstant.File_EMPTY_ERROR);
            callResult.setSuccess(false);
            return callResult;
        }
        try {

            String realImagePath = globalConfig.getUploadImagePath();
            File file = new File(realImagePath, lefile.getOriginalFilename());
            //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
            //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
            FileUtils.copyInputStreamToFile(lefile.getInputStream(), file);
            //返回map
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("localPath", file.getAbsolutePath());
            JSONObject jsonObject = JSON.parseObject(json);
            //上传图片后调detect接口
            if (jsonObject.containsKey("detectFace")) {
                DetectFaceRequestParam param = new DetectFaceRequestParam();
                param.setLocalPath(file.getAbsolutePath());
                CallResult result = faceDetectService.detectImage(param);
                if (result.isSuccess()) {
                    callResult.setSuccess(true);
                    map.put("json", result.getObject());
                } else {
                    callResult.setSuccess(false);
                    callResult.setMessage(result.getMessage());
                }
            }
            callResult.setObject(map);
            return callResult;
        } catch (Exception e) {
            callResult.setMessage(MyConstant.SYSTEM_ERROR + ":" + e.getMessage());
            callResult.setSuccess(false);
            return callResult;
        }
    }
}