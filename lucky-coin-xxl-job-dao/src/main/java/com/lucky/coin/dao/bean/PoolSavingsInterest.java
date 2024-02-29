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

/**
 * Created by yyh on 2020/8/21
 * 利息分发明细
 */
@Data
@Builder
@Table(name = "pool_savings_interest")
@AllArgsConstructor
@NoArgsConstructor
public class PoolSavingsInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uidPoolBinance;
    private Long day;
    /**
     * 申购的项目
     */
    private Long poolSavingsId;
    private Long coinId;
    /**
     * 0 是活期
     */
    private Integer type;
    /**
     * 生息资产
     */
    private BigDecimal validAmount;
    /**
     * 总申购金额 包含今日 暂时没用
     */
    private BigDecimal totalApplyAmount;
    /**
     * 今日利息金额
     */
    private BigDecimal interestAmount;
    /**
     * 年化率
     */
    private BigDecimal annualizedRate;
    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
