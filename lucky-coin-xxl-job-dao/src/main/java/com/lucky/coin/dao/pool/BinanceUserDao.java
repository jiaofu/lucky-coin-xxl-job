package com.lucky.coin.dao.pool;

import com.lucky.coin.dao.bean.PoolBinanceUserBean;

import java.util.Date;

/**
 * Created by yyh on 2020/3/17
 */
public interface BinanceUserDao {
    PoolBinanceUserBean getUidBinance(Long uidBinance);

    Integer getUidBinanceNum(Date start, Date end);
}
