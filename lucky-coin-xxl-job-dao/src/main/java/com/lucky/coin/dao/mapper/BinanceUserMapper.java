package com.lucky.coin.dao.mapper;

import com.lucky.coin.dao.bean.PoolBinanceUserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@org.apache.ibatis.annotations.Mapper
public interface BinanceUserMapper extends tk.mybatis.mapper.common.Mapper<PoolBinanceUserBean> {


    @Select("SELECT * FROM  pool_binance_user WHERE uid_binance = #{uidBinance}  limit 1")
    PoolBinanceUserBean getPoolBinanceUserId(@Param("uidBinance") long uidBinance);
}
