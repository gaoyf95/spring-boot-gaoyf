package com.gaoyf.common.VO;

import lombok.Getter;

/**
 * Created by 高宇飞 on 2018/10/15 10:18:37
 * 业务异常枚举
 */
@Getter
public enum ExceptionEnum implements BaseEnum {

    /**
     * 用户名不能为空
     */
    PARAM_NULL("400", "用户名不能为空");


    private String code;
    private String message;

    ExceptionEnum(String code, String message) {
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
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (exceptionEnum.getCode().equals(code)) {
                return exceptionEnum.getMessage();
            }
        }
        return null;
    }
}
