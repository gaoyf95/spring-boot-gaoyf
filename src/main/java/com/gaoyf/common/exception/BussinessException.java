package com.gaoyf.common.exception;

import com.gaoyf.common.VO.IBaseEnum;

/**
 * Created by 高宇飞 on 2018/4/2 14:54:35
 * 业务异常
 */
public class BussinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private IBaseEnum baseEnum;

    public BussinessException(IBaseEnum baseEnum) {
        super();
        this.baseEnum = baseEnum;
    }

    public IBaseEnum getBaseEnum() {
        return baseEnum;
    }

    public void setBaseEnum(IBaseEnum baseEnum) {
        this.baseEnum = baseEnum;
    }
}
