package com.afr.web.controller;

import com.afr.entrys.businessdomain.CompareFaceRequestParam;
import com.afr.service.facedetect.CompareFaceService;
import com.afr.utils.CallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yan liang
 * @create 2019/4/16
 * @since 1.0.0
 */
@Controller
public class CompareFaceController {
    @Autowired
    private CompareFaceService compareFaceService;

    @RequestMapping(value = "CompareFace/doCompare", method = RequestMethod.POST)
    @ResponseBody
    public CallResult doCompare(CompareFaceRequestParam param) {
        return compareFaceService.doCompare(param);
    }
}