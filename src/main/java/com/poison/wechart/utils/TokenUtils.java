package com.poison.wechart.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poison.wechart.vo.UrlData;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class TokenUtils {
    private CloseableHttpClient httpClient;
    private HttpGet httpGet;//用于获得登录后的页面
    private static Gson gson = new Gson();

    @Value("${weChart.corpId}")
    private String corpId;
    @Value("${weChart.corpSecret}")
    private String corpSecret;



    public String getToken() throws IOException {
        UrlData uData = new UrlData();
        uData.setGetTokenUrl(corpId, corpSecret);
        String resp = toAuth(uData.getGetTokenUrl());//就是按照GET方式拼接了字符串得到url
        Map<String, Object> map = gson.fromJson(resp,
                new TypeToken<Map<String, Object>>() {
                }.getType());
        System.out.println(map);
        return map.get("access_token").toString();
    }

    protected String toAuth(String Get_Token_Url) throws IOException {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(Get_Token_Url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.toString());
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            System.out.println(response.getAllHeaders());
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return resp;
    }
}
