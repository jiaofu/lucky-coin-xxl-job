package com.lucky.coin.service.http;

import com.lucky.coin.service.vo.http.CoinmarketcapCoinInfoVo;

import java.util.List;

public interface HttpFromMarketInfo {

    public CoinmarketcapCoinInfoVo getCoinInfoByList (List<String> coinNameList);

    public CoinmarketcapCoinInfoVo  getCoinInfoById (Long id);
}
