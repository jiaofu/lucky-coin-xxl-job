package com.lucky.coin.dao.pool.impl;

import com.lucky.coin.dao.bean.PoolBinanceUserBean;
import com.lucky.coin.dao.config.DBAnno;
import com.lucky.coin.dao.mapper.BinanceUserMapper;
import com.lucky.coin.dao.pool.BinanceUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * Created by yyh on 2020/3/17
 */
@Repository
public class BinanceUserDaoImpl implements BinanceUserDao {
    @Autowired
    BinanceUserMapper binanceUserMapper;

    @Override
    @DBAnno("dbRead")
    public PoolBinanceUserBean getUidBinance(Long uidBinance) {
        return binanceUserMapper.getPoolBinanceUserId(uidBinance);
    }

    @Override
    @DBAnno("dbRead")
    public Integer getUidBinanceNum(Date start, Date end) {
        Example example = new Example(PoolBinanceUserBean.class);
        example.createCriteria().andGreaterThanOrEqualTo("createdDate", start).andLessThan("createdDate", end);
        return binanceUserMapper.selectCountByExample(example);
    }

}
