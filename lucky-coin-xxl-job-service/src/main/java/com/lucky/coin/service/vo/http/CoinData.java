package com.lucky.coin.service.vo.http;

import java.util.Map;

@lombok.Data
public class CoinData {
    private Map<String, Coin> coins;
}
