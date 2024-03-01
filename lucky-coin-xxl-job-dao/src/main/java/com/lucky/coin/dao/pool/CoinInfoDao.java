package com.lucky.coin.dao.pool;


import com.lucky.coin.dao.bean.CoinInfoBean;

import java.util.List;

public interface CoinInfoDao {

    List<CoinInfoBean> getCoinInfo();

    Integer batchInsert(List<CoinInfoBean> beans);
}
