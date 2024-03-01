package com.lucky.coin.dao.pool.impl;


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
        Integer count = mapper.batchInsert(beans);
        return count;
    }

    @Override
    public List<MarketInfoBean> getAll() {
        Example example = new Example(MarketInfoBean.class);
        Example.Criteria criteria = example.createCriteria();

        return mapper.selectByExample(example);
    }
}
