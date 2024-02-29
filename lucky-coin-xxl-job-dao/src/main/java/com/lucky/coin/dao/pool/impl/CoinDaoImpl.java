package com.lucky.coin.dao.pool.impl;

import com.lucky.coin.dao.bean.PoolAlgoBean;
import com.lucky.coin.dao.bean.PoolCoinBean;
import com.lucky.coin.dao.bean.PoolCoinHashInfoBean;
import com.lucky.coin.dao.config.DBAnno;
import com.lucky.coin.dao.mapper.CoinMapper;
import com.lucky.coin.dao.pool.CoinDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CoinDaoImpl implements CoinDao {


    @Resource
    CoinMapper mapper;

    /**
     * 获取币种信息
     *
     * @param coinid
     * @return
     */
    @Override
    @DBAnno("dbRead")
    public PoolCoinBean getCoinHashInfoBean(long coinid) {
        PoolCoinBean coinBean = new PoolCoinBean();
        coinBean.setId(1L);
        return mapper.selectByPrimaryKey(coinBean);
        //return null;
    }
}
