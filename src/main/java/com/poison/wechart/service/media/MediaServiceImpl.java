package com.poison.wechart.service.media;

import com.poison.wechart.common.error.CodeException;
import com.poison.wechart.common.utils.DateUtils;
import com.poison.wechart.common.utils.JsonUtils;
import com.poison.wechart.utils.HttpsClientRequestFactory;
import com.poison.wechart.utils.TokenUtils;
import com.poison.wechart.utils.WriteText;
import com.poison.wechart.vo.Message;
import com.poison.wechart.vo.Result;
import com.poison.wechart.vo.WeChatData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class MediaServiceImpl implements MediaService {

    private static final Logger logger = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public String uploadMediaByPath(String path, String type) {
        //获取token
        String token = getToken();
        String url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload";
        url = url + "?access_token=" + token + "&type=" + type;
        RestTemplate rest = new RestTemplate(new HttpsClientRequestFactory());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        FileSystemResource resource = new FileSystemResource(new File(path));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("media", resource);

        Result result = rest.postForObject(url, param, Result.class);
        logger.info(result.toString());
        return result.getMedia_id();
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
