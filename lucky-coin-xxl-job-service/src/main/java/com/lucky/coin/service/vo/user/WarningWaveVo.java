package com.lucky.coin.service.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarningWaveVo {
    /**
     * 有效数量
     */
    private Long validNum = 0L;


    /**
     * 实时算力
     */
    private BigDecimal hashRate = BigDecimal.ZERO;


    /**
     * 算力波动率
     */
    private BigDecimal WaveRate = BigDecimal.ZERO;

    /**
     * 是否是是低于于有效算力
     */
    private boolean boolHashRate;

    /**
     * 是否是是低于于有效工控
     */
    private boolean boolValidNum;

    /**
     * 波动率
     */
    private boolean boolWaveRate;

}
