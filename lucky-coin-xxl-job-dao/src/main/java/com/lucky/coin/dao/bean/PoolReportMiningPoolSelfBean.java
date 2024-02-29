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
@Table(name = "pool_report_mining_pool_self")
@AllArgsConstructor
@NoArgsConstructor
public class PoolReportMiningPoolSelfBean extends BaseBean {
    private Long day; //'账单日期',
    private String coinName; //'币种名称缩写:btc',
    private BigDecimal selfHashrate;  //'币安算力',
    private Integer output; // '爆块数量',
    private BigDecimal outputIncome; // '爆块收益',


    private Integer partnerOutput; // '第三方爆块数量',
    private BigDecimal partnerOutputIncome; // '第三方爆块收益',

    private BigDecimal theoryExpenditure; // '理论支出',
    private BigDecimal feeIncome; // '手续费收入',
    private BigDecimal partnerTheoryExpenditure; //'合作方理论支出',
    private BigDecimal partnerHashrate; // '合作方算力',
    private BigDecimal partnerApportionProfit; // '合作方分摊盈亏',
    private BigDecimal partnerFeeIncome; // '合作方手续费收入',
    private BigDecimal partnerRateIncome; // '合作方中币安扣点收入',
    private BigDecimal innerIncome; // '币安内部1%手续费收入',
    private BigDecimal gunPoolProfit; // '机枪池盈亏',
    private BigDecimal gunPoolSmartProfit; //'机枪聚矿盈亏',
    private BigDecimal selfProfit; // '币安盈亏',

    private BigDecimal selfTheoryExpenditure; //自研的理论支出
    private BigDecimal poolGunProfitSum; // 机枪增强收益-机枪理论收益
    private BigDecimal lucky; // 'lucky',
    private BigDecimal price;// price 每日价格


    @Transient
    private long coinId;


}
