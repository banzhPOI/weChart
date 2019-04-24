package com.poison.wechart.vo;

import lombok.Data;

@Data
public class Department {

    private Long id;
    private String name;
    private Long parentid;
    private Integer order;
}
