package com.lucky.coin.dao.pool;

import com.lucky.coin.dao.bean.PoolCoinBean;
import com.lucky.coin.dao.bean.PoolCoinHashInfoBean;

public interface CoinDao {
    PoolCoinBean getCoinHashInfoBean(long coinid);

}
