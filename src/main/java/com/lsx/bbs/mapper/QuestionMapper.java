package com.lsx.bbs.mapper;

import com.lsx.bbs.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/20
 */

@Repository
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (Id,title,description,gmt_create,gmt_modified,creator,tag) " +
            "VALUES (#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
            public void insert(Question question);
}
