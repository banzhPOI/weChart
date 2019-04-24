package com.poison.wechart.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.poison.wechart.vo.UrlData;
import com.poison.wechart.vo.WeChatData;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SendMsgService {

    private CloseableHttpClient httpClient;
    private HttpPost httpPost;//用于提交登陆数据
    private HttpGet httpGet;//用于获得登录后的页面

    public static final String CONTENT_TYPE = "Content-Type";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
    private static Gson gson = new Gson();

    public void sendTextMsg(String toUser, String contentValue) throws IOException {
        String token = getToken("wwe118e5ecfce0635a", "moWFBcUbTpL5Mqk4hB1f9c-mgpZWYL2m6xd47TS5vZM");
        String postData = createPostData(toUser, "text", 1000002, "content", contentValue);
        String response = post("utf-8", SendMsgService.CONTENT_TYPE, (new UrlData()).getSendMessageUrl(), postData, token);
        System.out.println("获取到的token======>" + token);
        System.out.println("请求数据======>" + postData);
        System.out.println("发送微信的响应数据======>" + response);
    }


    public String getToken(String corpId, String corpSecret) throws IOException {
        SendMsgService sw = new SendMsgService();
        UrlData uData = new UrlData();
        uData.setGetTokenUrl(corpId, corpSecret);
        String resp = sw.toAuth(uData.getGetTokenUrl());//就是按照GET方式拼接了字符串得到url
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

    /**
     * 创建POST BODY
     */
    private String createPostData(String touser, String msgtype, int agent_id, String contentKey, String contentValue) {
        WeChatData weChatData = new WeChatData();
        weChatData.setTouser(touser);
        weChatData.setAgentid(agent_id);
        weChatData.setMsgtype(msgtype);
        Map<Object, Object> content = new HashMap<Object, Object>();
        content.put(contentKey, contentValue + "\n--------\n" + df.format(new Date()));
        weChatData.setText(content);
        System.out.println(gson.toJson(weChatData));
        return gson.toJson(weChatData);
    }

    /**
     * POST请求
     */
    private String post(String charset, String contentType, String url, String data, String token) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        httpPost = new HttpPost(url + token);
        httpPost.setHeader(CONTENT_TYPE, contentType);
        httpPost.setEntity(new StringEntity(data, charset));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, charset);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return resp;
    }

}
