package com.poison.wechart.service.department;

import com.poison.wechart.vo.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);


    @Override
    public List<Department> findAllDepartments(){
        List<Department>departments=new ArrayList<>();
        return departments;
    }
}
