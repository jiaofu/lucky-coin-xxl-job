package com.lucky.coin.service.vo.bot;

import com.lucky.coin.dao.bean.PoolCoinBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CoinBlockInfo {
    private PoolCoinBean coinBean;
    private String region;

    private BigDecimal theoryExpenditure;// 自研理论支出
    private BigDecimal income;// 收入
}
