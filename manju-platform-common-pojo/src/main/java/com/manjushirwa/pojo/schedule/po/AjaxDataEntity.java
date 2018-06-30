package com.manjushirwa.pojo.schedule.po;

import java.util.HashMap;
import java.util.Map;

public class AjaxDataEntity {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    /**
     * 返回200表示成功,400表示失败
     */
    private int code;
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 用户返回给浏览器的数据
     */
    private Map<String,Object> extend = new HashMap<>()  ;

    public static AjaxDataEntity success() {
        AjaxDataEntity result = new AjaxDataEntity();
        result.setCode(200);
        result.setMsg("处理成功");
        return result;
    }
    public static AjaxDataEntity fail(String msg) {
        AjaxDataEntity result = new AjaxDataEntity();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

    public AjaxDataEntity add(String key,Object value) {
        this.getExtend().put(key, value);
        return this;
    }

}
