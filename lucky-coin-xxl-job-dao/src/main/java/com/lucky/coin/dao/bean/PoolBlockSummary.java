package com.lucky.coin.dao.bean;

import lombok.AccessLevel;
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
 * Created by yyh on 2020/3/19
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "pool_btc_block_summary")
public class PoolBlockSummary {
    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long coinId;
    /**
     * 本日结束块高度
     */
    private Long blockHeight;
    /**
     * 本日第一个区块时间
     */
    private Date firstBlockTime;
    /**
     * 本日最后一个区块时间
     */
    private Date endBlockTime;
    /**
     * 汇总数据日期
     */
    private Long day;
    /**
     * 本日总共出多少块
     */
    private Long blockCount;
    /**
     * 本日汇总矿工费
     */
    private BigDecimal minerFee;
    /**
     * 本日汇总奖励
     */
    private BigDecimal reword;
    /**
     * 本日矿工费占挖矿收益百分比(minFee/(minFee+reword))
     */
    private BigDecimal minerFeeRate;
    /**
     * 难度
     */
    private BigDecimal difficulty;
    /**
     * 解区块时间
     */
    private Date createTime;
    private Date modifyTime;
}
