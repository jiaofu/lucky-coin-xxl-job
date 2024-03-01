package com.lucky.coin.service.http.impl;

import com.alibaba.fastjson.JSON;
import com.lucky.coin.service.config.SysConfig;
import com.lucky.coin.service.http.HttpFromMarketInfo;
import com.lucky.coin.service.util.OkHttpUtils;
import com.lucky.coin.service.vo.http.CoinmarketcapCoinInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HttpFromMarketInfoImpl implements HttpFromMarketInfo {

    @Resource
    SysConfig sysConfig;

    @Override
    public CoinmarketcapCoinInfoVo getCoinInfoByList (List<String> coinNameList){


        String coinNames = coinNameList.stream().collect(Collectors.joining(","));

        String url = String.format("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=%s",coinNames);
        Map<String, String> headers = new HashMap<>();
        headers.put("X-CMC_PRO_API_KEY",sysConfig.getXCmcProApiKey());
        String result = OkHttpUtils.httpGet(url,headers);


        log.info(" getCoinInfo coinName {}  result : {} ",coinNames, result);

        CoinmarketcapCoinInfoVo coinInfoVo = JSON.parseObject(result,CoinmarketcapCoinInfoVo.class);
        return coinInfoVo;
    }

    @Override
    public CoinmarketcapCoinInfoVo  getCoinInfoById (Long id){

        String url = String.format("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?id=4847");
        Map<String, String> headers = new HashMap<>();
        headers.put("X-CMC_PRO_API_KEY",sysConfig.getXCmcProApiKey());
        String result = OkHttpUtils.httpGet(url,headers);


        log.info(" getCoinInfo coinName {}  result : {} ",4847, result);
        return null;
    }

}
