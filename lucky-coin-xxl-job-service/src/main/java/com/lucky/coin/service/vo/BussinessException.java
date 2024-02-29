package com.lucky.coin.service.vo;

import com.lucky.coin.service.enums.ReturnCodeEnum;
import lombok.Data;

@Data
public class BussinessException extends Exception {
    private int code;
    private String msg;
    private ResultBean bean;

    public BussinessException(ResultBean bean) {
        super(bean.getMsg());
        this.bean = bean;
    }

    public BussinessException(ReturnCodeEnum returnCodeEnum) {
        bean = ResultBean.error(returnCodeEnum);
    }

}
