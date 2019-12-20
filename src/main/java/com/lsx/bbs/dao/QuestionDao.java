package com.lsx.bbs.dao;

import com.lsx.bbs.mapper.QuestionMapper;
import com.lsx.bbs.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/20
 */
@Component
public class QuestionDao {
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionDao(QuestionMapper questionMapper){
        this.questionMapper=questionMapper;
    }

    public void insertQuestion(Question question){
        questionMapper.insert(question);
    }
}
