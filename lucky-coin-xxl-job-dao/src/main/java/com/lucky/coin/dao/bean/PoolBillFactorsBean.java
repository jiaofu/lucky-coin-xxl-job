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
 * 记录出账时用到的矿工手续费率和币种转化价格
 * Created by yyh on 2020/5/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "pool_bill_factors")
public class PoolBillFactorsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long day;
    /**
     * btc矿工手续费率
     */
    private BigDecimal btcMinerFeeRate;
    /**
     * bch矿工手续费率
     */
    private BigDecimal bchMinerFeeRate;
    /**
     * bsv矿工手续费率
     */
    private BigDecimal bsvMinerFeeRate;
    /**
     * bchbtc价格
     */
    private BigDecimal bchBtcPrice;
    /**
     * bsvbtc价格
     */
    private BigDecimal bsvBtcPrice;

    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
