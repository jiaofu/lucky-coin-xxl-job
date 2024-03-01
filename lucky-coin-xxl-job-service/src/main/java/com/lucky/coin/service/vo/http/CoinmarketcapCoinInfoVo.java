package com.lucky.coin.service.vo.http;

import lombok.Data;

import java.util.Map;

@Data
public class CoinmarketcapCoinInfoVo {
    private Status status;
   private Map<String, Coin> data;
}
