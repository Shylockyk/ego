package com.ego.item.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * portal最终的数据格式
 *
 * @Author: yk
 * @Date: 2019/6/8 20:56
 */
public class PortalMenu implements Serializable {

    private List<Object> data;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
