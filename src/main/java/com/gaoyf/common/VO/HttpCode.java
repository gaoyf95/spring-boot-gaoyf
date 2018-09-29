package com.gaoyf.common.VO;

/**
 * Created by 高宇飞 on 2018/4/2 14:54:35
 */
public enum HttpCode {

    /**
     * 请求成功
     */
    OK("200", "请求成功"),

    /**
     * 参数异常
     */
    PARAM_ERROR("40", "参数异常"),

    /**
     * 请求失败
     */
    FAILURE("404", "请求失败"),

    /**
     * 请求成功
     */
    FORBIDDEN("403", "登陆信息已失效"),;


    private String value;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    HttpCode(String value, String content) {
        this.value = value;
        this.content = content;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 根据类型值获取类型名称
     *
     * @param value 类型值
     * @return 类型名称
     */
    public static String getContent(String value) {
        for (HttpCode httpCode : HttpCode.values()) {
            if (httpCode.getValue().equals(value)) {
                return httpCode.getContent();
            }
        }
        return null;
    }
}
