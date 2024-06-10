package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "coin_info")
public class CoinInfoBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//
    private Long day;// '日期',
    private Integer status;// '0是显示 1是隐藏'
    private String symbol;// 'symbol',
    private String slug;// 'slug',
    private String name;// '币种名称',
    private Long marketId;// 'market Id',
    private Long maxSupply;// '最大供应量',
    private Long totalSupply;// '当前供应量',
    private BigDecimal fullyDilutedMarketCap; // 总市值
    private Long coinRanking;//  '得分',
    private Long marketCapRank;//  '市值排名',
    private Date dbCreate_time;// '数据库插入时间，请勿修改',
    private Date dbModifyTime; // '数据库更新时间，请勿修改',
}


