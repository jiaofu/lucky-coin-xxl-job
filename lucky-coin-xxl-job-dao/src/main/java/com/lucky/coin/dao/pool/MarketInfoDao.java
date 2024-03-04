package com.lucky.coin.dao.pool;

import com.lucky.coin.dao.bean.MarketInfoBean;

import java.util.List;

public interface MarketInfoDao {
    Integer batchInsert(List<MarketInfoBean> beans);

    List<MarketInfoBean> getAll(Long day);


    List<MarketInfoBean> getByDay(Long day);
}
