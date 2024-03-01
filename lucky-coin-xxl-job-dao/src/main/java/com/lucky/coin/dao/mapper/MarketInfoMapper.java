package com.lucky.coin.dao.mapper;

import com.lucky.coin.dao.bean.MarketInfoBean;
import com.lucky.coin.dao.bean.PoolCoinBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface MarketInfoMapper extends tk.mybatis.mapper.common.Mapper<MarketInfoBean> {

    @Insert({
            "<script>",
            "INSERT INTO market_info(",
            " `day`, `coin_id`, `symbol`, `price`, `percent_change_24h`, `percent_change_7d`, `percent_change_30d`, `coin_ranking`, `coin_score`,`fully_diluted_market_cap`, `last_updated`) VALUES ",

            "<foreach collection=\"recordList\"  index=\"index\" item=\"record\" separator=\",\"> ",
            "(#{record.day,jdbcType=BIGINT},#{record.coinId,jdbcType=BIGINT},#{record.symbol,jdbcType=VARCHAR},",
            "#{record.price,jdbcType=DECIMAL},#{record.percentChange24h,jdbcType=DECIMAL},",
            "#{record.percentChange7d,jdbcType=DECIMAL},#{record.percentChange30d,jdbcType=DECIMAL}," ,
            "#{record.coinRanking,jdbcType=BIGINT},#{record.coinScore,jdbcType=BIGINT},#{record.fullyDilutedMarketCap,jdbcType=DECIMAL}," ,
                    "#{record.lastUpdated,jdbcType=TIMESTAMP})",
            "</foreach>",

            " ON DUPLICATE KEY UPDATE ",
            "day=VALUES(day)," ,
            "symbol=VALUES(symbol)," ,
            "price=VALUES(price)," ,
            "percent_change_24h=VALUES(percent_change_24h)," ,
            "percent_change_7d=VALUES(percent_change_7d)," ,
            "percent_change_30d=VALUES(percent_change_30d)," ,
            "coin_ranking=VALUES(coin_ranking)," ,
            "coin_score=VALUES(coin_score)," ,
            "fully_diluted_market_cap=VALUES(fully_diluted_market_cap),",
            "last_updated=VALUES(last_updated)" ,

            "</script>"
    })
    int batchInsert(@Param("recordList") List<MarketInfoBean> recordList);
}
