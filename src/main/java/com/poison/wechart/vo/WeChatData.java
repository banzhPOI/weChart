package com.poison.wechart.vo;

import lombok.Data;

@Data
public class WeChatData {

    private String touser;
    private String toparty;
    private String totag;
    private String msgtype;
    private Integer agentid;
    private Object file;
    private Object text;
    private String content;
    private Integer safe;
}
