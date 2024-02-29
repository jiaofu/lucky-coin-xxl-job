package com.lucky.coin.service.vo.http;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;


/**
 * 用户返回值
 */
@Data
@ApiModel("获取用户返回值")
public class UserListApiRet {
    @ApiModelProperty("0 正常，-1 异常")
    private int err_no;
    @ApiModelProperty("异常信息")
    private String err_msg;
    @ApiModelProperty("数据")
    private Map<String, Long> data;
}
