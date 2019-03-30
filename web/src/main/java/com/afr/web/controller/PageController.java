package com.afr.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yan liang
 * @create 2019/3/29
 * @since 1.0.0
 */
@Controller
public class PageController {
    @RequestMapping("/face/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/face/detect")
    public String detect() {
        return "detect";
    }

    @RequestMapping("/face/compare")
    public String compare() {
        return "compare";
    }

    @RequestMapping("/face/search")
    public String search() {
        return "search";
    }
}