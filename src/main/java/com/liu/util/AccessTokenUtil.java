package com.liu.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.liu.util.AccessTokenUtil.AccessToken;

public class AccessTokenUtil {

    private static final String APPID = "wx9c633a19827cf770";
    private static final String APPSECRET = "3cd267af944a970a2b2c2a0ba35120c0";
    private static final String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET + "";

    class AccessToken {

        private String access_token;
        private String expires_in;

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
    }

    //要自动调用它，当tAccess_token失效时
    public static String getAccessToken() {



        try {
            //从access-token.properties读取
            //String path=AccessTokenUtil.class.getResource("").getPath();
            Properties pro = new Properties();
            pro.load(new FileInputStream("F:/WX-publicNo/target/classes/access-token.properties"));
            String accesstoken = (String) pro.get("access_token");
            String getTimesrt = (String) pro.get("get_time");
            System.out.println("accesstoken:" + accesstoken);
            if (accesstoken != null && !"".equals(accesstoken)) {
               // System.out.println("getaccesstoken:" + accesstoken);
                System.out.println("accsstoken is not null");
                Long getTime = Long.parseLong(getTimesrt);
                if ((System.currentTimeMillis() - getTime) / 1000 < 7200) {
                    return accesstoken;
                }
            }
            //创建httpclient对象
            HttpClient client = HttpClients.createDefault();
            //创建get对象
            HttpGet get = new HttpGet(URL);
            //执行请求
            HttpResponse response = client.execute(get);
            //获得返回的entity对象
            HttpEntity entity = response.getEntity();
            //JSON数据包给公众号：{"access_token":"ACCESS_TOKEN","expires_in":7200}
            String json = EntityUtils.toString(entity);

            //使用gson工具包
            Gson gson = new Gson();
            AccessToken token = gson.fromJson(json, AccessToken.class);

            //保存access_token值
            pro.setProperty("access_token", token.getAccess_token());
            pro.setProperty("get_time", System.currentTimeMillis() + "");
            pro.store(new FileOutputStream("F:/WX-publicNo/target/classes/access-token.properties"), System.currentTimeMillis() + "");
            //token.get
            return token.getAccess_token();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;

    }

    public static void main(String[] args) {
        try {
            String token = getAccessToken();
            //System.out.println("token="+token);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
