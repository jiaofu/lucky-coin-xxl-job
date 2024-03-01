package com.lucky.coin.dao.pool;

import com.lucky.coin.dao.bean.PoolCoinBean;

public interface CoinDao {
    PoolCoinBean getCoinHashInfoBean(long coinid);

}
