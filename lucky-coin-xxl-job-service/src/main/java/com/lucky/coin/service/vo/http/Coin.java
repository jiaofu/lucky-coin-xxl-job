package com.lucky.coin.service.vo.http;

import lombok.Data;

import java.util.List;

@Data
public class Coin {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private int num_market_pairs;
    private String date_added;
    private List<String> tags;
    private long max_supply;
    private long circulating_supply;
    private long total_supply;
    private int is_active;
    private Boolean infinite_supply;
    private Object platform;  // 未在JSON数据中给出具体类型，根据actual数据修改
    private int cmc_rank;
    private int is_fiat;
    private Object self_reported_circulating_supply;  // 未在JSON数据中给出具体类型，根据actual数据修改
    private Object self_reported_market_cap;  // 未在JSON数据中给出具体类型，根据actual数据修改
    private String tvl_ratio;  // 未在JSON数据中给出具体类型，根据actual数据修改
    private String last_updated;
    private Quote quote;
}
