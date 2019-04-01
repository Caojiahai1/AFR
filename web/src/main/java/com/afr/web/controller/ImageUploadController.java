package com.afr.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Yan liang
 * @create 2019/4/1
 * @since 1.0.0
 */
@Controller
public class ImageUploadController {

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile lefile, HttpServletRequest request, HttpServletResponse response) {
        //获取输出流
        OutputStream os= null;
        try {
            os = new FileOutputStream("E:/" + lefile.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=lefile.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();
            return null;
        } catch (Exception e) {
            return "fail";
        }
    }
}