package com.lucky.coin.dao.pool;

import com.lucky.coin.dao.bean.PoolAlgoBean;
import com.lucky.coin.dao.bean.PoolAlgoCoinBean;
import com.lucky.coin.dao.bean.PoolCoinBean;

import java.util.List;

public interface AlgoCoinDao {


    List<PoolAlgoCoinBean> getPoolAlgoCoinBeans(int status);


    List<PoolAlgoBean> getPoolAlgoBeans(int status);


    List<PoolCoinBean> getPoolCoinBeans(int status);


    List<PoolAlgoCoinBean> getAlgoCoinByAlgo(long algoId, int status);

    PoolAlgoCoinBean getAlgoCoinByCoin(int coinId);
}
