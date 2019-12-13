package com.lsx.bbs.service;

import com.lsx.bbs.dao.UserDao;
import com.lsx.bbs.dto.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @Version 1.0
 * @Author:lsx
 * @Date:2019/12/10
 */
@Service
public class UserService {
    private final
    UserDao userDao;
    private final
    GitHubUser gitHubUser;
    @Autowired
    public UserService(UserDao userDao, GitHubUser gitHubUser) {
        this.userDao = userDao;
        this.gitHubUser = gitHubUser;
    }
    public int addUser() throws SQLException {
        return userDao.insertUser(gitHubUser);
    }
    public int deleteUser() throws Exception {
        return userDao.deleteUserById(gitHubUser);
    }
}
