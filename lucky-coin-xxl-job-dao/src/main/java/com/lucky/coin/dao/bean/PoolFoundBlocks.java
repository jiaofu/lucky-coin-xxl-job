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
 * Created by yyh on 2020/6/14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_found_blocks")
public class PoolFoundBlocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long coinId;
    private String region;
    private Long puid;
    private String workerFullName;
    private Long height;
    private BigDecimal rewords;
    private BigDecimal baseRewards;

    private Integer blockType;//0:普通用户爆块 1：机枪池用户爆块
    private Integer blockStatus;//0:有效区块 1：孤块

    private String hash;
    private String preHash;
    private Long blockDay;
    private Long blockTime;
    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
