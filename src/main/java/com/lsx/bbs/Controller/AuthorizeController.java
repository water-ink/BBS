package com.lsx.bbs.Controller;

import com.lsx.bbs.dto.AccessTokenDTO;
import com.lsx.bbs.dto.GitHubUser;
import com.lsx.bbs.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class AuthorizeController {

    @Autowired
    GitHubProvider gitHubProvider;

    @RequestMapping("/callback")
    public String Authorize(@RequestParam(name="code")String code,
                            @RequestParam(name="state")String state) throws IOException {

//        InputStream is = AuthorizeController.class.getClassLoader().getResourceAsStream("authorize.properties");
//        Properties prop = new Properties();
//        prop.load(is);

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setClient_id("c033421872b2455bf926");
        accessTokenDTO.setClient_secret("59a539e29f10f80bb66a5e236db015cd17b2c9b2");
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);

        System.out.println(user.getName());

//        accessTokenDTO.setClient_id(prop.getProperty("client_id"));
//        accessTokenDTO.setRedirect_uri(prop.getProperty("uri"));
//        accessTokenDTO.setClient_secret(prop.getProperty("client_secret"));

        return "main";
    }

}
