package com.lucky.coin.dao.pool.impl;

import com.lucky.coin.dao.bean.PoolAlgoBean;
import com.lucky.coin.dao.bean.PoolAlgoCoinBean;
import com.lucky.coin.dao.bean.PoolCoinBean;
import com.lucky.coin.dao.config.DBAnno;
import com.lucky.coin.dao.mapper.AlgoCoinMapper;
import com.lucky.coin.dao.mapper.AlgoMapper;
import com.lucky.coin.dao.mapper.CoinMapper;
import com.lucky.coin.dao.pool.AlgoCoinDao;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AlgoCoinDaoImpl implements AlgoCoinDao {
    @Resource
    AlgoCoinMapper algoCoinMapper;

    @Resource
    CoinMapper coinMapper;
    @Resource
    AlgoMapper algoMapper;

    @Override
    @DBAnno("dbRead")
    public List<PoolAlgoBean> getPoolAlgoBeans(int status) {
        Example example = new Example(PoolAlgoBean.class);
        example.createCriteria().andEqualTo("status", status);
        return algoMapper.selectByExample(example);
    }

    @Override
    @DBAnno("dbRead")
    public List<PoolAlgoCoinBean> getPoolAlgoCoinBeans(int status) {
        return algoCoinMapper.getPoolAlgoCoinBeans(status);
    }


    @Override
    @DBAnno("dbRead")
    public List<PoolCoinBean> getPoolCoinBeans(int status) {
        return coinMapper.getPoolCoinBeans(status);
    }


    @Override
    @DBAnno("dbRead")
    public List<PoolAlgoCoinBean> getAlgoCoinByAlgo(long algoId, int status) {
        Example example = new Example(PoolAlgoCoinBean.class);
        example.createCriteria()
                .andEqualTo("status", status)
                .andEqualTo("algoId", algoId);
        return algoCoinMapper.selectByExample(example);
    }

    @Override
    @DBAnno("dbRead")
    public PoolAlgoCoinBean getAlgoCoinByCoin(int coinId) {
        Example example = new Example(PoolAlgoCoinBean.class);
        example.createCriteria()
                .andEqualTo("status", 0)
                .andEqualTo("coinId", coinId);
        return algoCoinMapper.selectOneByExample(example);
    }
}
