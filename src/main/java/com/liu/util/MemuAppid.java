package com.liu.util;


import com.google.gson.Gson;
import com.sun.deploy.net.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MemuAppid {

    private static final String APPID = "wx9c633a19827cf770";
    private static final String APPSECRET = "3cd267af944a970a2b2c2a0ba35120c0";
    private static final String URL = "http://e5vk5y.natappfree.cc/WX_publicNo_war_exploded/islogin";
    private static final String GET_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9c633a19827cf770&redirect_uri=http://e5vk5y.natappfree.cc/WX_publicNo_war_exploded/islogin&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";


    public static void getCode() {

        try {
            //创建对象
            HttpClient client = HttpClients.createDefault();


            HttpGet get = new HttpGet(GET_URL);
            //执行请求
            HttpResponse execute = client.execute(get);

            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println("s=" + s);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String getApid(String code) {

        String token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        System.out.println("this is getApid");
        try {

            //创建对象
            HttpClient client = HttpClients.createDefault();


            HttpGet get = new HttpGet(token_url);
            //执行请求
            HttpResponse execute = client.execute(get);

            HttpEntity entity = execute.getEntity();
            String s = EntityUtils.toString(entity);
            System.out.println("access_token=" + s);

            Gson gs = new Gson();
            accessToken token = gs.fromJson(s, accessToken.class);
            return token.getOpenid();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    class accessToken {
        private String access_token;
        private String expires_in;
        private String refresh_token;
        private String openid;
        private String scope;


        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }


}
