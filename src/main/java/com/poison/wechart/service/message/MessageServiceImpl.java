package com.poison.wechart.service.message;

import com.poison.wechart.common.error.CodeException;
import com.poison.wechart.common.utils.DateUtils;
import com.poison.wechart.common.utils.JsonUtils;
import com.poison.wechart.service.media.MediaService;
import com.poison.wechart.utils.HttpsClientRequestFactory;
import com.poison.wechart.utils.TokenUtils;
import com.poison.wechart.utils.WriteText;
import com.poison.wechart.vo.Employee;
import com.poison.wechart.vo.Message;
import com.poison.wechart.vo.Result;
import com.poison.wechart.vo.WeChatData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    MediaService mediaService;
    @Value("${weChart.agentId}")
    private Integer agentId;
    @Value("${file.errorPath}")
    private String errorPath;

    @Override
    public void sendMsg(Message message){
        //获取token
        String token = getToken();
        String url="https://qyapi.weixin.qq.com/cgi-bin/message/send";
        url=url+"?access_token="+token;
        String path =errorPath+ DateUtils.getNowStr()+".txt";
        //生成文件
        try {
            WriteText.writeToText(message.getContent(),path);
        }catch (Exception e){
            throw new CodeException(-1,"生成日志文件失败");
        }
        //上传文件
        String media_id =mediaService.uploadMediaByPath(path,"file");
        //制作请求体
        WeChatData weChatData=new WeChatData();
        String touser="";
        List<String>userIds=message.getUserIds();
        for (int i=0;i<userIds.size();i++){
            touser+=userIds.get(i);
            if (!(i==userIds.size()-1)){
                touser+="|";
            }
        }
        weChatData.setTouser(touser);
        weChatData.setMsgtype("file");
        weChatData.setAgentid(agentId);
        weChatData.setToparty("");
        weChatData.setTotag("");
        weChatData.setSafe(0);
        Map<Object, Object> file = new HashMap<Object, Object>();
        file.put("media_id", media_id);
        weChatData.setFile(file);
        String requestJson=JsonUtils.ObjectToJson(weChatData);
        String result=post(url,requestJson);
        logger.info(result);
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


    private static String post(String url, String requestJson){
        logger.debug(requestJson);
        RestTemplate rest=new RestTemplate(new HttpsClientRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
        String result = rest.postForObject(url, entity, String.class);
        logger.debug(result);
        return result;
    }
}
