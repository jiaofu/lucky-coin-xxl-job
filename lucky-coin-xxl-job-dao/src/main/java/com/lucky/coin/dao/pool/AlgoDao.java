package com.lucky.coin.dao.pool;

import com.lucky.coin.dao.bean.PoolAlgoBean;

import java.util.List;

/**
 * @author yyh
 * @date 2020/3/11
 */
public interface AlgoDao {
    List<PoolAlgoBean> getPoolAlgoBeans(int status);

    PoolAlgoBean findById(Long algoId);
}
