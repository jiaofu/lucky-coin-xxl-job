package com.lucky.coin.service.vo.http;

import lombok.Data;

@Data
public class BachSignRet {
    private String rawTransaction;
    private String sign_hash;
    private String signature;
}
