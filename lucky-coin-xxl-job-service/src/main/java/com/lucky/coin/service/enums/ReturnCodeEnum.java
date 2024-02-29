package com.lucky.coin.service.enums;


/**
 * 业务返回码
 */
public enum ReturnCodeEnum {
    GROUP_NAME_NULL(-001030, "组名不能为空", ""),
    GROUP_NAME_SPECIAL(-001031, "组名不能包含特殊字符", ""),
    GROUP_EXCEED_NUM(-001032, "最多支持20个分组", ""),
    GROUP_EXISTS(-001033, "分组已经存在", ""),
    GROUP_NOT_VALID(-001034, "没有该有效分组", ""),
    WORKER_NOT_EXISTS(-001035, "不存在该矿工", ""),


    BINANCE_TRAN_ID_ERROE(-005000, "无法获取分发的订单号", ""),
    BINANCE_TRAN_ID_FAIL(-005001, "无法获取分发的失败", ""),
    BINANCE_CONFIRM_ERROE(-005002, "提交分发失败", ""),
    BINANCE_QUERY_ERROE(-005003, "查询分发失败", ""),
    CHANGE_STATUS_PAYING(-005004, "改变订单状态为支付中失败", ""),
    SEND_MSG_FAIL(-005005, "发送短息失败", ""),

    PARAM_ERROR(-001002, "参数错误", "Param error"),
    DISCONNECTED(-1001, "内部错误", "service busy or error"),


    SYSTEM_ERROR(-3, "系统异常", "fail"),
    FAIL(-1, "失败", "fail"),
    SUCCESS(0, "成功", "success");
    private int code;
    private String msg;
    private String desc;

    ReturnCodeEnum(int code, String desc, String message) {
        this.code = code;
        this.desc = desc;
        this.msg = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
