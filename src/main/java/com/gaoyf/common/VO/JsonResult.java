package com.gaoyf.common.VO;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by 高宇飞 on 2016/9/12.
 * Json结果
 */
@ApiModel(value = "Json结果", description = "Json结果")
@Data
public class JsonResult {
    @JsonView(BaseViews.Public.class)
    @ApiModelProperty(value = "是否成功", name = "code", example = "200")
    private String code;//是否成功
    @JsonView(BaseViews.Public.class)
    @ApiModelProperty(value = "消息", name = "message", example = "请求成功")
    private String message;//消息
    @JsonView(BaseViews.Public.class)
    @ApiModelProperty(value = "额外的数据", name = "data", example = "{}")
    private Object data;//额外的数据

    public JsonResult(String code, String message, Object data) {
        this(code, message);
        this.data = data;
    }

    public JsonResult(String code) {
        this.code = code;
    }

    public JsonResult(String code, String message) {
        this(code);
        this.message = message;
    }

    /**
     * 成功的Json结果
     */
    private static final JsonResult code_instance = new JsonResult(HttpCode.OK.getCode(), HttpCode.OK.getMessage());

    /**
     * 获取成功的Json结果
     *
     * @return 成功的Json结果
     */
    public static JsonResult getCode_instance() {
        return code_instance;
    }

    /**
     * 失败的Json结果
     */
    private static final JsonResult code_fail = new JsonResult(HttpCode.FAILURE.getCode(), HttpCode.FAILURE.getMessage());

    /**
     * 获取失败的Json结果
     *
     * @return 失败的Json结果
     */
    public static JsonResult getCode_fail() {
        return code_fail;
    }

    public JsonResult() {
    }
}
