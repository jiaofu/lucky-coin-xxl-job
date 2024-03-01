package com.lucky.coin.service.vo.market;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoinRankVo {

    private String symbol;
    private Long coinScore;// '得分',
}
