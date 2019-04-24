package com.poison.wechart.controller;

import com.poison.wechart.common.json.Response;
import com.poison.wechart.common.json.ResponseHelper;
import com.poison.wechart.service.department.DepartmentService;
import com.poison.wechart.service.employee.EmployeeService;
import com.poison.wechart.vo.Department;
import com.poison.wechart.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //部门列表
    @RequestMapping(value = "department/{departmentId}", method = RequestMethod.GET)
    public Response<?> findEmployeesByDepartmentId(@PathVariable Long departmentId,Integer fetch_child) {
        List<Employee>employees=employeeService.findEmployeesByDepartmentId(departmentId,fetch_child);
        return ResponseHelper.createSuccessResponse(employees);
    }

}
