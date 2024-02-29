package com.lucky.coin.service.vo.http;

import lombok.Data;

@Data
public class FeeByTxidVo {
    private String txid;
    private Long fee;
}
