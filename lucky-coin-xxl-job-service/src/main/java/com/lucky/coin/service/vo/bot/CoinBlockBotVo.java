package com.lucky.coin.service.vo.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CoinBlockBotVo {
    private BigDecimal selfProfit; // '自研爆块收益-自研理论支出',
    private BigDecimal selfLucky;// 自研爆块收益/自研理论支出
    private BigDecimal partnerProfit; // '大路盈亏=大陆爆块收益-大陆理论支出',
    private BigDecimal partnerLucky; // '（大陆爆块收益/合作方理论支出',
    private BigDecimal partnerApportionProfit; // '合作方分摊盈亏 （partnerProfit/2）',
    private BigDecimal allLucky;//  总收益/总理论支出
    private BigDecimal selfAllProfit;//（自研爆块收益-自研理论支出）+（总爆块收益-自研理论支出-合作方理论支出）*0.5
}
