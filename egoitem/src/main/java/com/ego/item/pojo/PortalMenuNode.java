package com.ego.item.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 每个节点的类型
 *
 * @Author: yk
 * @Date: 2019/6/8 20:57
 */
public class PortalMenuNode implements Serializable {

    private String u;

    private String n;

    private List<Object> i;

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public List<Object> getI() {
        return i;
    }

    public void setI(List<Object> i) {
        this.i = i;
    }
}
