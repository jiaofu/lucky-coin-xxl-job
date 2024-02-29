package com.lucky.coin.service.vo.http;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EstimatesmartfeeVo {
    BigDecimal feerate;
    Integer blocks;
}
