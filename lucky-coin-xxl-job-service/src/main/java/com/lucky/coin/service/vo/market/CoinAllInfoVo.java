package com.lucky.coin.service.vo.market;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoinAllInfoVo {
    @JSONField(ordinal = 1)
    private String symbol;

    @JSONField(ordinal = 2)
    private Long coinScore;// '得分',
    @JSONField(ordinal = 3)
    private Long fee;// '排名速率',
}
