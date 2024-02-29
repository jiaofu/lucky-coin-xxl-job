package com.lucky.coin.service.vo.http;

import lombok.Data;

@Data
public class KeygenRet {
    private String address;
    private String public_key_hex;
    private String public_key_signature_hex;
}
