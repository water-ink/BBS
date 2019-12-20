package com.lsx.bbs.controller;

import com.lsx.bbs.mapper.UserMapper;
import com.lsx.bbs.model.User;
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
    private final UserMapper userMapper;

    public FastLoginController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("/")
    public String fastLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies)
            if("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user = userMapper.select(token);
                if (user != null){
                    request.getSession().setAttribute("user",user.getName());
                    request.getSession().setAttribute("id",user.getId());
                    return "redirect:/main";
                }
                else
                    break;
            }


        return "index";
    }
}
