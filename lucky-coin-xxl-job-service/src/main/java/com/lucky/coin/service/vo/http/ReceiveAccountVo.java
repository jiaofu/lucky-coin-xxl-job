package com.lucky.coin.service.vo.http;

import lombok.Data;

@Data
public class ReceiveAccountVo {
    // address 必须放前面
    private String address;
    // 单位是聪
    private String amount;

}
