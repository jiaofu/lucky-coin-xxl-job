package com.lucky.coin.service.vo.http;


import lombok.Data;

import java.util.List;

@Data
public class DecodeRawTransactionVo {
    private String txid;
    private String hash;
    private int version;
    private int size;
    private int vsize;
    private int weight;
    private int locktime;
    private List<DecodeRawVinVo> vin;
    private List<DecodeRawVoutVo> vout;
}
