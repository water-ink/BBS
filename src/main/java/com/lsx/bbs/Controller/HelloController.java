package com.lsx.bbs.Controller;

import com.lsx.bbs.dto.GitHubUser;
import com.lsx.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class HelloController {
    private final
    GitHubUser gitHubUser;
    private final
    UserService userService;
    @Autowired
    public HelloController(GitHubUser gitHubUser, UserService userService) {
        this.gitHubUser = gitHubUser;
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String Hello(@RequestParam(name = "name")String name, Model model) throws SQLException {
        model.addAttribute("name", name);
        gitHubUser.setId((long) 1001);
        userService.addUser();
        return "index";
    }
}
