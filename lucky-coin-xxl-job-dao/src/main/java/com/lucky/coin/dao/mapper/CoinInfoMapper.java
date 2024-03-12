package com.lucky.coin.dao.mapper;

import com.lucky.coin.dao.bean.CoinInfoBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface CoinInfoMapper extends tk.mybatis.mapper.common.Mapper<CoinInfoBean> {


    @Insert({
            "<script>",
            "INSERT INTO coin_info(",
            " `day`, `symbol`, `slug`, `name`, `market_id`, `max_supply`, `total_supply`,`fully_diluted_market_cap`,`coin_ranking`,`market_cap_rank`) VALUES ",

            "<foreach collection=\"recordList\"  index=\"index\" item=\"record\" separator=\",\"> ",
            "(#{record.day,jdbcType=BIGINT},#{record.symbol,jdbcType=VARCHAR},#{record.slug,jdbcType=VARCHAR},",
            "#{record.name,jdbcType=VARCHAR},#{record.marketId,jdbcType=BIGINT},",
            "#{record.maxSupply,jdbcType=DECIMAL},#{record.totalSupply,jdbcType=DECIMAL},#{record.fullyDilutedMarketCap,jdbcType=DECIMAL}," ,
                    "#{record.coinRanking,jdbcType=BIGINT},#{record.marketCapRank,jdbcType=BIGINT})",
            "</foreach>",

            " ON DUPLICATE KEY UPDATE ",
            "day=VALUES(day)," ,
           "slug=VALUES(slug)," ,
                    "name=VALUES(name)," ,
                    "symbol=VALUES(symbol)," ,
                    "max_supply=VALUES(max_supply)," ,
                    "total_supply=VALUES(total_supply),",
            "coin_ranking=VALUES(coin_ranking),",
            "market_cap_rank=VALUES(market_cap_rank),",
            "fully_diluted_market_cap=VALUES(fully_diluted_market_cap)",
            "</script>"
    })
    int batchInsert(@Param("recordList") List<CoinInfoBean> recordList);

}
