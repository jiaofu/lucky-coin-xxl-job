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
 * Created by yyh on 2020/7/21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_earn_fpps")
public class PoolEarnFppsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long puid;//为0表示平台数据
    private Long coinId;
    private String region;//用户数据为0，平台数据表示节点
    private Long day;
    private BigDecimal fppsEarn;
    private BigDecimal earn;
    private BigDecimal hashRate;
    private BigDecimal todayBlockReword;
    private Integer todayBlockCount;
    private Integer todayOrphanedCount;

    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
