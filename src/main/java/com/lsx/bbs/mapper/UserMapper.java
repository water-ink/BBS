package com.lsx.bbs.mapper;

import com.lsx.bbs.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/12
 */
@Repository
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO github_user(userId,userName,token,gmt_create,gmt_modified) VALUES(#{userId},#{userName},#{token},#{gmt_create},#{gmt_modified})")
    public void insert(User user);
    @Select("SELECT * FROM github_user WHERE token=#{token}")
    public User select(String token);
}
