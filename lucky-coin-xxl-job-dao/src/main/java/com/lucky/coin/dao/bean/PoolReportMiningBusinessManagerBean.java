package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@Table(name = "pool_report_mining_business_manager")
@AllArgsConstructor
@NoArgsConstructor
public class PoolReportMiningBusinessManagerBean extends BaseBean {

    private Long day;// '日期',
    private String businessManager;//'商务管理员',
    private BigDecimal dayHashRate;// '日均算力',
    private Long coinId;//'币种id(真正发给用户的币种)',
    private BigDecimal serviceChargeFee;// ' 手续费支出 ',
    private BigDecimal rebatePayment;// ' 返点 ',
    private BigDecimal blocksRewards;//' 爆块收益 ',
    private BigDecimal userPayment;// 支付费用
    private BigDecimal returnCommission;// 返佣金额
    private BigDecimal returnCash;// 返现金额
}
