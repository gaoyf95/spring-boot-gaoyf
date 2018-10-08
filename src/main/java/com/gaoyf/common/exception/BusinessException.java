package com.gaoyf.common.exception;

import com.gaoyf.common.VO.BaseEnum;
import lombok.Data;

/**
 * Created by 高宇飞 on 2018/10/7 14:54:35
 * 业务异常
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private BaseEnum baseEnum;

    public BusinessException(BaseEnum baseEnum) {
        super();
        this.baseEnum = baseEnum;
    }

}
