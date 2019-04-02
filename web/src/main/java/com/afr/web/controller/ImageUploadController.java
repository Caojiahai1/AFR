package com.afr.web.controller;

import com.afr.utils.CallResult;
import com.afr.utils.GloabConfig;
import com.afr.utils.MyConstant;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author Yan liang
 * @create 2019/4/1
 * @since 1.0.0
 */
@Controller
public class ImageUploadController {

    @Autowired
    private GloabConfig gloabConfig;

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    @ResponseBody
    public CallResult upload(MultipartFile lefile, HttpServletRequest request, HttpServletResponse response) {
        CallResult callResult = new CallResult();
        if (lefile.isEmpty()) {
            callResult.setMessage(MyConstant.File_EMPTY_ERROR);
            callResult.setSuccess(false);
            return callResult;
        }
        try {
            String realImagePath = gloabConfig.getUploadImagePath();
            File file = new File(realImagePath, lefile.getOriginalFilename());
            //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
            //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
            FileUtils.copyInputStreamToFile(lefile.getInputStream(), file);
            callResult.setSuccess(true);
            callResult.setObject(file.getAbsolutePath());
            return callResult;
        } catch (Exception e) {
            callResult.setMessage(MyConstant.SYSTEM_ERROR + ":" + e.getMessage());
            callResult.setSuccess(false);
            return callResult;
        }
    }
}