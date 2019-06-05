package com.ego.manager.controller;

import com.ego.commons.utils.FtpUtil;
import com.ego.manager.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: yk
 * @Date: 2019/6/5 21:26
 */

@Controller
public class PicController {

    @Resource
    private PicService picServiceImpl;

    @RequestMapping("pic/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile uploadFile) {
        return picServiceImpl.upload(uploadFile);
    }

}
