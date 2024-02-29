package com.lucky.coin.dao.pool.impl;

import com.lucky.coin.dao.bean.PoolAlgoBean;
import com.lucky.coin.dao.config.DBAnno;
import com.lucky.coin.dao.mapper.AlgoMapper;
import com.lucky.coin.dao.pool.AlgoDao;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yyh
 * @date 2020/3/11
 */
@Repository
public class AlgoDaoImpl implements AlgoDao {

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
    public PoolAlgoBean findById(Long algoId) {
        PoolAlgoBean algo = new PoolAlgoBean();
        algo.setId(algoId);
        return algoMapper.selectByPrimaryKey(algo);
    }
}
