package com.poison.wechart.vo;

import lombok.Data;

import java.util.List;

@Data
public class Result {
    private Integer errcode;
    private String errmsg;
    private Object department;
}
