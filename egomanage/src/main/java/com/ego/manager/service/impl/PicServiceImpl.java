package com.ego.manager.service.impl;

import com.ego.commons.utils.FtpUtil;
import com.ego.manager.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: yk
 * @Date: 2019/6/5 21:36
 */
@Service
public class PicServiceImpl implements PicService {

    @Value("${ftpclient.host}")
    private String host;

    @Value("${ftpclient.port}")
    private int port;

    @Value("${ftpclient.username}")
    private String username;

    @Value("${ftpclient.password}")
    private String password;

    @Value("${ftpclient.basePath}")
    private String basePath;

    @Value("${ftpclient.filePath}")
    private String filePath;

    @Override
    public Map<String, Object> upload(MultipartFile uploadFile) {

        Map<String, Object> map = new HashMap<>();
        map.put("error", 1);
        map.put("msg", "图片上传失败");
        String oldName = uploadFile.getOriginalFilename();
        if(oldName == null) return map;
        String fileName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
        boolean result = false;
        try {
            result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, uploadFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (result) {
                map.put("error", 0);
                map.put("url", "http://" + host + ":80" + filePath + fileName);
            }
        }
        return map;
    }
}
