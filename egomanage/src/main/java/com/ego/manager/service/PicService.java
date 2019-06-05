package com.ego.manager.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: yk
 * @Date: 2019/6/5 21:34
 */
public interface PicService {

    /**
     * 文件上传
     * @param uploadFile
     * @return
     */
    Map<String, Object> upload(MultipartFile uploadFile);

}
