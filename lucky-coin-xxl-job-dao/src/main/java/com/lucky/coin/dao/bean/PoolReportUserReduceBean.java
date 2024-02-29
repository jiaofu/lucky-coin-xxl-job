package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Table(name = "pool_report_user_reduce")
@AllArgsConstructor
@NoArgsConstructor
public class PoolReportUserReduceBean extends BaseBean {

    private Long day;// '账单日期',
    private Long uidPoolBinance;//'本人矿池Id',
    private Long algoId;//'算法id（算力的算法）',
    private BigDecimal highestHashRate;//'最高算力',
    private Date lastShareTime;// '算力最后接入时间',
}
