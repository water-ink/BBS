package com.lsx.bbs.controller;

import com.lsx.bbs.dao.QuestionDao;
import com.lsx.bbs.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/20
 */
@Controller
public class PublishController {
    private final QuestionDao questionDao;

    @Autowired
    PublishController(QuestionDao questionDao){
        this.questionDao=questionDao;
    }
    @PostMapping("/publish")
    public String publish(Question question , HttpServletRequest httpServletRequest){
        Long id;
        id = (Long) httpServletRequest.getSession().getAttribute("id");
        question.setId(id);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionDao.insertQuestion(question);
        return "redirect:/main";
    }
}
