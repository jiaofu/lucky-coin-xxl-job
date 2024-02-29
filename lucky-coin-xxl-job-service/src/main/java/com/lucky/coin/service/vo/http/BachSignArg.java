package com.lucky.coin.service.vo.http;

import lombok.Data;

import java.util.List;

@Data
public class BachSignArg {
    private String pub;
    private FundAccountVo fund_account;
    private List<ReceiveAccountVo> receive_account;
    private Long byte_fee;
}
