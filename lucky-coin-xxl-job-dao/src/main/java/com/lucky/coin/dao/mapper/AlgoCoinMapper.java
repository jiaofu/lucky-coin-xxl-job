package com.lucky.coin.dao.mapper;

import com.lucky.coin.dao.bean.PoolAlgoCoinBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface AlgoCoinMapper extends tk.mybatis.mapper.common.Mapper<PoolAlgoCoinBean> {


    @Select("SELECT * FROM  pool_algo_coin WHERE status = #{status} ")
    List<PoolAlgoCoinBean> getPoolAlgoCoinBeans(@Param("status") int status);


}
