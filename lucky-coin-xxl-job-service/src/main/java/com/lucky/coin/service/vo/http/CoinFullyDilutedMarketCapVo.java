package com.lucky.coin.service.vo.http;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CoinFullyDilutedMarketCapVo {

    /**
     * 币种
     */
    private String name;
    private String symbol;// 'symbol',
    private String slug;// 'slug',
    private Long marketId;// 'market Id',
    private Long maxSupply;// '最大供应量',
    private Long totalSupply;// '当前供应量',
    private BigDecimal fullyDilutedMarketCap; // 总市值

    private Long coinId;// '币种Id',
    private Long day;//'日期',

    private BigDecimal price;//  '价格',
    @Column(name = "percent_change_24h")
    private BigDecimal percentChange24h;//  '24小时价格',
    @Column(name = "percent_change_7d")
    private BigDecimal percentChange7d;// '7天价格变化',
    @Column(name = "percent_change_30d")
    private BigDecimal percentChange30d;//  '30日安价格价格',
    private Long coinRanking;//  '本次排名',
    private Long coinScore;// '本次得分',
    private Date lastUpdated;// '行情更新时间',




}
