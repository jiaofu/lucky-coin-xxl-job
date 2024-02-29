package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by yyh on 2022/2/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckPpsEarnVo {
    private Long day;
    //    private BigDecimal perHashEarn;//每T收益
    private BigDecimal difficulty;
    private BigDecimal blockReword;
    private BigDecimal totalHash;//总算力
    private BigDecimal earn;//a池总收益
    private BigDecimal totalNetWorkEarn;//a池总理论收益
    private BigDecimal gap;//误差
}
