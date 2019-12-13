package com.lsx.bbs.dao;

import com.lsx.bbs.dto.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/10
 */
 @Repository
public class UserDao {
    private final
    JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertUser(GitHubUser gitHubUser) throws SQLException {
        return jdbcTemplate.update("INSERT INTO github_user(userId,userName,bio) VALUES(?,?,?)",gitHubUser.getId(),gitHubUser.getName(),gitHubUser.getBio());
    }
    public int deleteUserById(GitHubUser gitHubUser) throws Exception{
        return jdbcTemplate.update("DELETE FROM github_user WHERE userId=?",gitHubUser.getId());
    }
    
}
