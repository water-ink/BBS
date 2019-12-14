package com.lsx.bbs.controller;

import com.lsx.bbs.mapper.UserMapper;
import com.lsx.bbs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/14
 */
@Controller
public class FastLoginController {
    @Autowired private UserMapper userMapper;
    @RequestMapping("/")
    public String fastLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies)
            if("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user = userMapper.select(token);
                if (user != null)
                    return "main";
                else
                    break;
            }


        return "index";
    }
}
