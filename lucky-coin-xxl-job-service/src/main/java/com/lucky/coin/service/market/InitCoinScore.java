package com.lucky.coin.service.market;

import com.lucky.coin.dao.bean.CoinInfoBean;
import com.lucky.coin.dao.bean.MarketInfoBean;

/**
 * 初始化数据
 */
public interface InitCoinScore {


    /**
     * 初始化币种
     */
    void  initSymbol(String yBase);



     void initMarketByThanDay(Long day);






}
