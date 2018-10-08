package com.gaoyf.common.VO;

/**
 * Created by 高宇飞 on 2018/10/7 14:54:35
 * 通用枚举信息接口
 */
public interface BaseEnum {

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    String getCode();

    /**
     * 获取消息
     *
     * @return 消息
     */
    String getMessage();
}
