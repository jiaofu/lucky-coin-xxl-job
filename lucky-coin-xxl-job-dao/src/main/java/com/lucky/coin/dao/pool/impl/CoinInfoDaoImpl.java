package com.lucky.coin.dao.pool.impl;

import com.lucky.coin.dao.bean.CoinInfoBean;
import com.lucky.coin.dao.mapper.CoinInfoMapper;
import com.lucky.coin.dao.pool.CoinInfoDao;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CoinInfoDaoImpl implements CoinInfoDao {

    @Resource
    CoinInfoMapper mapper;

    @Override
    public List<CoinInfoBean> getCoinInfo() {
        Example example = new Example(CoinInfoBean.class);
        Example.Criteria criteria = example.createCriteria();

        return mapper.selectByExample(example);
    }

    @Override
    public Integer batchInsert(List<CoinInfoBean> beans) {
        if(beans == null || beans.size()==0){
            return 0;
        }
        Integer count = mapper.batchInsert(beans);
        return count;
    }
}
