package com.gaoyf.common.VO;

import lombok.Getter;

/**
 * Created by 高宇飞 on 2018/10/7 14:54:35
 */
@Getter
public enum HttpCode implements BaseEnum {

    /**
     * 请求成功
     */
    OK("200", "请求成功"),

    /**
     * 参数异常
     */
    PARAM_ERROR("400", "参数异常"),

    /**
     * 请求失败
     */
    FAILURE("404", "请求失败"),

    /**
     * 登陆信息已失效
     */
    FORBIDDEN("403", "登陆信息已失效");


    private String code;
    private String message;

    HttpCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据类型值获取类型名称
     *
     * @param code 类型值
     * @return 类型名称
     */
    public static String getContent(String code) {
        for (HttpCode httpCode : HttpCode.values()) {
            if (httpCode.getCode().equals(code)) {
                return httpCode.getMessage();
            }
        }
        return null;
    }
}
