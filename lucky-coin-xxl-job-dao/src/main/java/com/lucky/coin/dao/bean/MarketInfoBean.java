package com.lucky.coin.dao.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "market_info")
public class MarketInfoBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//,
    private Long coinId;// '币种Id',
    private Long day;//'日期',
    private String symbol;//'币种名称缩写:btc',
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
    private BigDecimal fullyDilutedMarketCap; // 总市值
    private Date dbCreateTime;//数据库插入时间，请勿修改',
    private Date dbModifyTime;//'数据库更新时间，请勿修改',
}



