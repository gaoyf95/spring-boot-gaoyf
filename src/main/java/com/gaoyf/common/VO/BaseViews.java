package com.gaoyf.common.VO;

/**
 * Created by 高宇飞 on 2015/10/11.
 * 基础Views(基于JsonView)
 */
public final class BaseViews {
    /**
     * 标志所有对象都可以直接访问
     */
    public interface Public {
    }

    /**
     * ListDTO对象的子集可以直接访问
     */
    public interface PartListDTO extends Public {
    }

    /**
     * 上传图片
     */
    public interface uploadDTO extends PartListDTO {
    }

}
