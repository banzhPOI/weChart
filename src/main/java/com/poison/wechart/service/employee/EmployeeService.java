package com.poison.wechart.service.employee;

import com.poison.wechart.vo.Department;
import com.poison.wechart.vo.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findEmployeesByDepartmentId(Long departmentId,Integer fetch_child);
}
