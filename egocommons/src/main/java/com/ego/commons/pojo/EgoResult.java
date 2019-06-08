package com.ego.commons.pojo;

import java.io.Serializable;

public class EgoResult implements Serializable {

    private int status;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
