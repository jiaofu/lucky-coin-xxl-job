package com.lucky.coin.dao.pool;

import com.lucky.coin.dao.bean.MarketInfoBean;

import java.util.List;

public interface MarketInfoDao {
    Integer batchInsert(List<MarketInfoBean> beans);

    List<MarketInfoBean> getLessThanOrEqualTo(Long day);


    List<MarketInfoBean> getGreaterThanOrEqualTo(Long day);

    List<MarketInfoBean> getByDay(Long day);


    /**
     * 获取币种的market 的信息
     * @param day
     * @param market
     * @return
     */
    MarketInfoBean getMarketInfo(Long day,String market);



}
