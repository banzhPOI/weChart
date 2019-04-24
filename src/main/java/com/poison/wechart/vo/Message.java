package com.poison.wechart.vo;

import lombok.Data;

import java.util.List;

@Data
public class Message {
    private String content;
    private List<String> userIds;
}
