package com.lucky.coin.service.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HashRateUtil {

    private static BigDecimal satoshi = new BigDecimal("0.00000001");
    private static BigDecimal wei = new BigDecimal("0.000000000000000001");

    /**
     * 计算 算力
     *
     * @param name
     * @param value
     * @return
     */
    public static long getHashRate(String name, long value) {

        return 0;
    }

    /**
     * reject_15m+stale_15m / (reject_15m + accept_15m + stale_15m)
     * 计算拒绝率
     */
    public static double getRejectRate(Long accept15m, Long stale15m, Long reject15m) {
        if (accept15m + reject15m + stale15m == 0) {
            return 0d;
        }
        return (double) (reject15m + stale15m) / (accept15m + reject15m + stale15m);
    }

    // earn * (1 + net_fee) * (1 - pool_fee)
    public static BigDecimal getEarnAmount(BigDecimal earn, BigDecimal netFee, BigDecimal poolFee) {
        return earn.multiply(BigDecimal.ONE.add(netFee)).multiply(BigDecimal.ONE.subtract(poolFee));
    }

    public static Double getRejectRate(BigDecimal shareAccept, BigDecimal shareStale, BigDecimal shareReject) {
        if (shareAccept.add(shareStale).add(shareReject).compareTo(BigDecimal.ZERO) == 0) {
            return 0d;
        }
        return (shareReject.add(shareStale)).divide(
                shareAccept.add(shareStale).add(shareReject), 12, RoundingMode.DOWN
        ).doubleValue();
    }

    public static BigDecimal toCoinNormalAmount(String coinName, BigDecimal amount) {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        if (coinName != null && StringUtils.equalsIgnoreCase(coinName, "eth") || StringUtils.equalsIgnoreCase(coinName, "etc")) {
            return amount.multiply(wei).stripTrailingZeros();
        } else {
            return amount.multiply(satoshi).stripTrailingZeros();
        }
    }

    public static BigDecimal toBtcAmount(BigDecimal amount) {
        return amount.multiply(satoshi);
    }

    //根据历史数据的speed和reject计算新的reject
    //rejectNew = (speed1*r1+speed2*r2)/(speed1+speed2+speed1*r1+speed2*r2)
    //r1 = reject1/(1-reject1)
    public static Double getRejectBySpeed(BigDecimal speed1, Double reject1, BigDecimal speed2, Double reject2) {
        if (reject1 == null) {
            reject1 = 0d;
        }
        if (reject2 == null) {
            reject2 = 0d;
        }
        if (speed1 == null) {
            speed1 = BigDecimal.ZERO;
        }
        if (speed2 == null) {
            speed2 = BigDecimal.ZERO;
        }
        //有一个speed是0，拒绝率按另一个显示
        if (speed1.compareTo(BigDecimal.ZERO) == 0) {
            return reject2;
        }
        //有一个speed是0，拒绝率按另一个显示
        if (speed2.compareTo(BigDecimal.ZERO) == 0) {
            return reject1;
        }
        double r1 = reject1 / (1 - reject1);
        double r2 = reject2 / (1 - reject2);
        BigDecimal speedReject = speed1.multiply(BigDecimal.valueOf(r1)).add(speed2.multiply(BigDecimal.valueOf(r2)));
        BigDecimal fenmu = speed1.add(speed2).add(speedReject);
        if (fenmu.compareTo(BigDecimal.ZERO) == 0) {
            return 0d;
        }
        return speedReject.divide(fenmu, 4, RoundingMode.HALF_UP).doubleValue();
    }

    //当前算力波动=（当前小时算力-昨日日均算力）/昨日日均算力*100%
    public static Double getHashWave(BigDecimal hashRate, BigDecimal yesterdayHash) {
        if (yesterdayHash == null || yesterdayHash.compareTo(BigDecimal.ZERO) == 0) {
            if (hashRate.compareTo(BigDecimal.ZERO) > 0) {
                return 666666d;
            }
            return 0d;
        }
//        BigDecimal yesterdayHourHash = yesterdayHash.divide(Constants.AVG_DAY_HOUR,2,RoundingMode.HALF_UP);
        return (hashRate.subtract(yesterdayHash)).divide(yesterdayHash, 8, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(getRejectBySpeed(new BigDecimal("0"), 1.0, new BigDecimal("0.00"), 1.0));
    }
}
