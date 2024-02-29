package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by richard.ww@binance.com on 2022/4/25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PuidDailyLuckyVo {

    private String puid;

    private String uidPoolBinance;

    private String poolUsername;

    private String regions;

    private String since;

    private String found;

    private String paid;

    private String lucky;

    private String nett;

    private String totalUidFound;

    private String totalUidPaid;

    private String totalUidLucky;

    private String totalUidNett;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CalcPuidDailyLucky {
        private BigDecimal found;

        private BigDecimal paid;

        private BigDecimal lucky;

        private BigDecimal nett;
    }
}
