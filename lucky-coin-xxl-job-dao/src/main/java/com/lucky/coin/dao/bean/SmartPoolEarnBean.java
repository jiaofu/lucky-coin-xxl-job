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
 * Created by yyh on 2020/5/25
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "smart_pool_earn")
public class SmartPoolEarnBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long day;
    private Long puid;
    private Long coinId;
    private BigDecimal speed;
    private BigDecimal theoryEarn;
    private BigDecimal enhanceEarn;
    private Integer type; // DEFAULT '0' COMMENT '0:聚矿 1: 自研',
    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
