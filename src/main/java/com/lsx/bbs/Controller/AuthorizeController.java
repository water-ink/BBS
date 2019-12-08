package com.lsx.bbs.Controller;

import com.lsx.bbs.dto.AccessTokenDTO;
import com.lsx.bbs.dto.GitHubUser;
import com.lsx.bbs.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.uri}")
    private String clientUri;
    @Value("${github.client.secret}")
    private String clientSecret;

    @RequestMapping("/callback")
    public String Authorize(@RequestParam(name="code")String code,
                            @RequestParam(name="state")String state,
                            HttpServletRequest request) throws IOException {

//        InputStream is = AuthorizeController.class.getClassLoader().getResourceAsStream("authorize.properties");
//        Properties prop = new Properties();
//        prop.load(is);

//        accessTokenDTO.setClient_id(prop.getProperty("client_id"));
//        accessTokenDTO.setRedirect_uri(prop.getProperty("uri"));
//        accessTokenDTO.setClient_secret(prop.getProperty("client_secret"));

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clientUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        if(user != null){
            request.getSession().setAttribute("user",user.getName());
            return "redirect:main";
        }


        return "redirect:main";
    }

}
