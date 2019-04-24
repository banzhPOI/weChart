package com.poison.wechart.controller;

import com.poison.wechart.common.json.Response;
import com.poison.wechart.common.json.ResponseHelper;
import com.poison.wechart.service.department.DepartmentService;
import com.poison.wechart.vo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //部门列表
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response<?> findAllDepartments(@PathVariable Long id) {
        List<Department>departments=departmentService.findAllDepartments(id);
        return ResponseHelper.createSuccessResponse(departments);
    }

}
