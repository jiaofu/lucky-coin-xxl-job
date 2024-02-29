package com.lucky.coin.service.vo.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WaringHashVo {
    private int sendCount;
    private BigDecimal lastHashRate;
    private long lastValidNum;
    private long lasetSendTime;

    /**
     * s 算力波动
     */
    private Boolean higherThanWave = false;

    /**
     * 算力波动率
     */
    private BigDecimal WaveRate = BigDecimal.ZERO;
}
