package com.lucky.coin.service.vo.http;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Currency {
    private BigDecimal price;
    private BigDecimal volume_24h;
    private BigDecimal volume_change_24h;
    private BigDecimal percent_change_1h;
    private BigDecimal percent_change_24h;
    private BigDecimal percent_change_7d;
    private BigDecimal percent_change_30d;
    private BigDecimal percent_change_60d;
    private BigDecimal percent_change_90d;
    private BigDecimal market_cap;
    private BigDecimal market_cap_dominance;
    private BigDecimal fully_diluted_market_cap;
    private Object tvl;  // 未在JSON数据中给出具体类型，根据actual数据修改
    private String last_updated;
}
