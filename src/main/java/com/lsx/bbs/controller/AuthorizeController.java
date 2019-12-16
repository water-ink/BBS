package com.lsx.bbs.controller;

import com.lsx.bbs.dto.AccessTokenDTO;
import com.lsx.bbs.dto.GitHubUser;
import com.lsx.bbs.mapper.UserMapper;
import com.lsx.bbs.model.User;
import com.lsx.bbs.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthorizeController {

    private final GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.uri}")
    private String clientUri;
    @Value("${github.client.secret}")
    private String clientSecret;
    private final UserMapper userMapper;
    @Autowired
    public AuthorizeController(GitHubProvider gitHubProvider, UserMapper userMapper) {
        this.gitHubProvider = gitHubProvider;
        this.userMapper = userMapper;
    }

    @RequestMapping("/callback")
    public String Authorize(@RequestParam(name="code")String code,
                            @RequestParam(name="state")String state,
                            HttpServletRequest request,
                            HttpServletResponse response) throws IOException {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clientUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);

        if(gitHubUser != null){
            User user = new User();
            user.setToken(accessToken);
            user.setId(gitHubUser.getId());
            user.setName(gitHubUser.getName());
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",accessToken));
            request.getSession().setAttribute("user",user.getName());
            return "redirect:main";
        }

        return "redirect:main";
    }

}
