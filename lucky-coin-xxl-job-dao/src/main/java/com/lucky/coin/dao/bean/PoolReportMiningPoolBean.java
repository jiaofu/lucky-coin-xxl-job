package com.lucky.coin.dao.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Data
@Builder
@Table(name = "pool_report_mining_pool")
@AllArgsConstructor
@NoArgsConstructor
public class PoolReportMiningPoolBean extends BaseBean {


    private Long day;// '账单日期',
    private Integer newUsers;//  '新增用户数',
    private Integer userNumber;//  '接入算力用户数'
    private String coinName;//  '币种名称缩写:btc',
    private BigDecimal poolHashrate;//  '矿池算力',
    private BigDecimal allHashrate;//  '全网算力'
    private Integer output;//  '爆块数',
    private BigDecimal outputIncome;//  '收入',
    private BigDecimal expenditure;//  '总支出',
    private BigDecimal profit;//  '盈亏',
    private BigDecimal lucky;//  'lucky',


    @Transient
    private long coinId;
}
