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
 * Created by yyh on 2020/4/3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pool_difficulty_info")
public class PoolDifficultyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long minute;
    private String coinName;
    private BigDecimal difficulty;
    private Date createDate;
}
