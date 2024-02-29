package com.lucky.coin.service.vo.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class DecodeRawVinVo {
    private String txid;
    private int vout;
    @JSONField(name = "scriptSig")
    private DecodeRawScriptsig scriptsig;
    private List<String> txinwitness;
    private Long sequence;
}
