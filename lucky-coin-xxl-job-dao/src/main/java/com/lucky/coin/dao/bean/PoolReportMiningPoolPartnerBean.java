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
@Table(name = "pool_report_mining_pool_partner")
@AllArgsConstructor
@NoArgsConstructor
public class PoolReportMiningPoolPartnerBean extends BaseBean {
    private Long day; //'账单日期',
    private String coinName; //'币种名称缩写:btc',
    private Integer output; // '爆块数量',
    private BigDecimal partnerHashrate; // '合作方算力',
    private BigDecimal outputIncome; // '爆块收益',
    private BigDecimal apportionProfit; // '分摊盈亏',
    private BigDecimal theoryExpenditure; // '理论支出',
    private BigDecimal partnerFeeIncome; //'手续费收入',
    private BigDecimal partnerRateIncome; // '合作方中币安手续费收入',
    private BigDecimal profit; // '盈亏',
    private BigDecimal lucky; // 'lucky',

    @Transient
    private long coinId;


}
