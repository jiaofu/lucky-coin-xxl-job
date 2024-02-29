package com.lucky.coin.service.vo.http;

import lombok.Data;

@Data
public class DecodeRawScriptpubkey {
    private String asm;
    private String hex;
    private String address;
    private String type;
}
