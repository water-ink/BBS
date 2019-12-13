package com.lsx.bbs.provider;

import com.alibaba.fastjson.JSON;
import com.lsx.bbs.dto.AccessTokenDTO;
import com.lsx.bbs.dto.GitHubUser;
import com.lsx.bbs.mapper.UserMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(string);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token=" + access_token)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String string = null;
                if (response.body() != null) {
                    string = response.body().string();
                }
                GitHubUser gitHubUser;
                gitHubUser = JSON.parseObject(string,GitHubUser.class);
                return gitHubUser;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }


}
