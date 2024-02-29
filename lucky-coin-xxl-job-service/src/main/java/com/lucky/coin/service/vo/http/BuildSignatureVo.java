package com.lucky.coin.service.vo.http;

import lombok.Data;

@Data
public class BuildSignatureVo {
    private String origin;
    private String signature;
}
