package com.poison.wechart.service.message;

import com.poison.wechart.vo.Employee;
import com.poison.wechart.vo.Message;

import java.util.List;

public interface MessageService {

    void sendMsg(Message message);

    void sendFile(Message message);

    void send(Message message);
}
