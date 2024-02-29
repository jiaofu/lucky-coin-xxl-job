package com.lucky.coin.service.vo;

import com.lucky.coin.service.enums.ReturnCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回结果")
public class ResultBean<T> {
    // code枚举 用于错误返回code msg
    @ApiModelProperty("code 为0 代表正常，负数代表请求错误")
    private int code;
    @ApiModelProperty("错误信息")
    private String msg;
    //数据
    private T data;

    public ResultBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResultBean(ReturnCodeEnum returnCodeEnum, T data) {
        this.code = returnCodeEnum.getCode();
        this.msg = returnCodeEnum.getMsg();
        this.data = data;

    }

    public static ResultBean error(ReturnCodeEnum returnCodeEnum) {
        return new ResultBean(returnCodeEnum, null);
    }

    public static ResultBean ok(Object data) {
        return new ResultBean(ReturnCodeEnum.SUCCESS.getCode(), "", data);
    }


}
