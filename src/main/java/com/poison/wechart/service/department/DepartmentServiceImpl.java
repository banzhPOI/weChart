package com.poison.wechart.service.department;

import com.poison.wechart.common.error.CodeException;
import com.poison.wechart.common.utils.JsonUtils;
import com.poison.wechart.utils.HttpsClientRequestFactory;
import com.poison.wechart.utils.TokenUtils;
import com.poison.wechart.vo.Department;
import com.poison.wechart.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service

public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    TokenUtils tokenUtils;


    @Override
    public List<Department> findAllDepartments(Long id) {
        //获取token
        RestTemplate rest = new RestTemplate(new HttpsClientRequestFactory());
        String token = getToken();
        String url="https://qyapi.weixin.qq.com/cgi-bin/department/list";
        url=url+"?access_token="+token+"&id="+id;
        String result = rest.getForObject(url, String.class);
        logger.debug(result);
        Result json= JsonUtils.JsonToObject(result.toString(), Result.class);
        List<Department> departments = (List<Department>) json.getDepartment();
        return departments;
    }

    private String getToken() {
        String token;
        try {
            token = tokenUtils.getToken();
        } catch (Exception e) {
            throw new CodeException(-1, "获取token失败");
        }
        return token;
    }
}
