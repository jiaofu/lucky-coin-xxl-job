package com.lucky.coin.service.vo.market;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoinRankFeeVo {

    private String symbol;
    private Long fee;// '排名速率',
}
