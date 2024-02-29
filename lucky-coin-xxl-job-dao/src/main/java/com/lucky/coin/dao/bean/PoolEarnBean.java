package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by yyh on 2020/4/30
 * <p>
 * 各区每个puid的收益earn情况
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_earn")
@ToString
public class PoolEarnBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long puid;
    private String region;
    private Long coinId;
    private BigDecimal speed;
    private BigDecimal earn;
    /**
     * 补偿的收益，可以为负
     */
    private BigDecimal extraEarn;
    private Long day;
    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',

    @Transient
    private String dsEarn;


}
