package me.hzhou.model;

import io.swagger.annotations.ApiModel;

/**
 * Created by hzhou on 2/7/17.
 * Email: i@hzhou.me
 */

@ApiModel(value = "Test POJO of Hello")
public class Hello {
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
