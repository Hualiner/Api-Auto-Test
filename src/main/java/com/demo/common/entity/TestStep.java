package com.demo.common.entity;


import com.demo.common.enums.HttpType;

import java.util.HashMap;
import java.util.Map;


public class TestStep {

    /**
     * 请求类型：post get
     */
    private HttpType type;
    /**
     * 接口
     */
    private String path;
    /**
     * 请求携带的参数，key、value格式
     */
    private Map<String, Object> params = new HashMap<>();
    /**
     * 消息体
     */
    private String body;

    public HttpType getType() {
        return type;
    }

    public void setType(HttpType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
