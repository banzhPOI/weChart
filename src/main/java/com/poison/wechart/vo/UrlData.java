package com.poison.wechart.vo;

import lombok.Data;

@Data

public class UrlData {

    String corpId;
    String corpSecret;
    String getTokenUrl;
    String sendMessageUrl;

    public void setGetTokenUrl(String corpid, String corpsecret) {
        this.getTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret;
    }

    public String getSendMessageUrl() {
        sendMessageUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
        return sendMessageUrl;
    }
}