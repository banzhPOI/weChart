package com.poison.wechart.controller;

import com.poison.wechart.common.json.Response;
import com.poison.wechart.common.json.ResponseHelper;
import com.poison.wechart.service.media.MediaService;
import com.poison.wechart.service.message.MessageService;
import com.poison.wechart.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/medias")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response<?> uploadMedia(String path,String type) {
        String media_id=mediaService.uploadMediaByPath(path,type);
        return ResponseHelper.createSuccessResponse(media_id);
    }
}
