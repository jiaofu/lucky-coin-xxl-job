package com.lucky.coin.service.vo.http;

import lombok.Data;

import java.util.List;

@Data
public class GetRawTransactionVo {
    private String txid;
    private String hash;
    private Integer version;
    private Integer size;
    private Integer vsize;
    private Integer weight;
    private Integer locktime;
    private List<DecodeRawVinVo> vin;
    private List<DecodeRawVoutVo> vout;
    private String hex;
    private String blockhash;
    private Long confirmations;
    private Long time;
    private Long blocktime;
}
