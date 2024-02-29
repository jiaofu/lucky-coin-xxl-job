package com.lucky.coin.dao.mapper;

import com.lucky.coin.dao.bean.PoolCoinBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CoinMapper extends tk.mybatis.mapper.common.Mapper<PoolCoinBean> {
    @Select("SELECT * FROM pool_coin WHERE status = #{status} order by pool_index ")
    List<PoolCoinBean> getPoolCoinBeans(@Param("status") int status);
}
