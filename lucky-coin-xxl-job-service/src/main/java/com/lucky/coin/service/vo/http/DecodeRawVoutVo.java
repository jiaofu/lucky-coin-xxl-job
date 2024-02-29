package com.lucky.coin.service.vo.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class DecodeRawVoutVo {
    private BigDecimal value;
    private int n;
    @JSONField(name = "scriptPubKey")
    private DecodeRawScriptpubkey scriptpubkey;
}
