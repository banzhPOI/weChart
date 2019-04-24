package com.poison.wechart.vo;

import lombok.Data;

@Data
public class Result {

    private Integer errcode;
    private String errmsg;
    private Object department;//部门列表
    private Object userlist;//员工列表
}
