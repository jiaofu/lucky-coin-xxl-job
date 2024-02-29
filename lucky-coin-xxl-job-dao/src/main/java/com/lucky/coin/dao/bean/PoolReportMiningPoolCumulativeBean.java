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
@Table(name = "pool_report_mining_pool_cumulative")
@AllArgsConstructor
@NoArgsConstructor
public class PoolReportMiningPoolCumulativeBean extends BaseBean {
    private Long day;// '账单日期',
    private Long userNum;// '用户总数',
    private Long userHashrateNum;// '接入算力的总用户数',
    private BigDecimal income;// '总收入',
    private BigDecimal expenditure;// '总支出',
    private BigDecimal partnerExpenditure;// '第三方支出',
    private BigDecimal profit;// '总盈亏',
    private BigDecimal lucky;// 'lucky',

    private BigDecimal partnerOutputIncome;//' 合作方爆块累计收益',
    private BigDecimal partnerTheoryExpenditure;// '合作方理论累计支出',
    private BigDecimal partnerLucky;//合作伙伴总luck',


    @Transient
    private PoolReportMiningPoolCumulativeBean laseBean;


}
