package com.poison.wechart.controller;

import com.poison.wechart.common.json.Response;
import com.poison.wechart.common.json.ResponseHelper;
import com.poison.wechart.service.employee.EmployeeService;
import com.poison.wechart.service.message.MessageService;
import com.poison.wechart.vo.Employee;
import com.poison.wechart.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    //发消息
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> sendMsg(@RequestBody Message message) {
        messageService.send(message);
        return ResponseHelper.createSuccessResponse();
    }

}
