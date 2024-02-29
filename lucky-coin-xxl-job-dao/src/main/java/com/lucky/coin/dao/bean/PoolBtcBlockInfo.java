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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "pool_btc_block_info")
public class PoolBtcBlockInfo {
    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 区块高度 唯一索引
     */
    private Long blockHeight;
    /**
     * 区块日期：20200319
     */
    private Long day;
    /**
     * 区块时间
     */
    private Date blockTime;
    /**
     * 矿工费
     */
    private BigDecimal minerFee;
    /**
     * 本块奖励
     */
    private BigDecimal reword;
    /**
     * 交易总数
     */
    private Integer txCount;
    /**
     * 难度
     */
    private BigDecimal difficulty;
    /**
     * 上一次hash
     */
    private String preHash;
    /**
     * 当前hash
     */
    private String curHash;
    /**
     * 当前version
     */
    private Integer curVersion;
    /**
     * 解区块时间
     */
    private Date decodeTime;
}