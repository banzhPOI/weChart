package com.poison.wechart.controller;

import com.poison.wechart.common.json.Response;
import com.poison.wechart.common.json.ResponseHelper;
import com.poison.wechart.service.department.DepartmentService;
import com.poison.wechart.utils.TokenUtils;
import com.poison.wechart.vo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("rest/test")
public class TestController {

    @Autowired
    TokenUtils tokenUtils;

    //获取token
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<?> findAllDepartments() throws IOException {
        String token= tokenUtils.getToken();
        return ResponseHelper.createSuccessResponse(token);
    }

}
