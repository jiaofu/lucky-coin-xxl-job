package com.lucky.coin.dao.pool.impl;


import com.google.common.collect.Lists;
import com.lucky.coin.dao.bean.CoinInfoBean;
import com.lucky.coin.dao.bean.MarketInfoBean;
import com.lucky.coin.dao.mapper.MarketInfoMapper;
import com.lucky.coin.dao.pool.MarketInfoDao;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class MarketInfoDaoImpl implements MarketInfoDao {


    @Resource
    MarketInfoMapper mapper;

    @Override
    public Integer batchInsert(List<MarketInfoBean> beans) {
        if(beans == null || beans.size()==0){
            return 0;
        }
        List<List<MarketInfoBean>> parts = Lists.partition(beans,10000);
        Integer count =0;
        for( List<MarketInfoBean> part :    parts){
            Integer countPart = mapper.batchInsert(part);
            count = count+countPart;
        }

        return count;
    }

    @Override
    public List<MarketInfoBean> getByDay(Long day) {
        Example example = new Example(MarketInfoBean.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("day",day);
        return mapper.selectByExample(example);
    }

    @Override
    public MarketInfoBean getMarketInfo(Long day, String symbol) {
        Example example = new Example(MarketInfoBean.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("day",day).andEqualTo("symbol",symbol);
        return mapper.selectOneByExample(example);
    }

    @Override
    public List<MarketInfoBean> getLessThanOrEqualTo(Long day) {
        Example example = new Example(MarketInfoBean.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andLessThanOrEqualTo("day",day);
        return mapper.selectByExample(example);
    }

    @Override
    public List<MarketInfoBean> getGreaterThanOrEqualTo(Long day) {
        Example example = new Example(MarketInfoBean.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andGreaterThanOrEqualTo("day",day);
        return mapper.selectByExample(example);

    }
}
