package com.lsx.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/19
 */
@Controller
public class HyperlinkController {
    @GetMapping("/publish_page")
    public String publish(){
        return "publish";
    }
}
