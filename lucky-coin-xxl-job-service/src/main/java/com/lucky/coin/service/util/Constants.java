package com.lucky.coin.service.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2020/3/11
 */
public class Constants {
    public static final String BTC_CURRENCY = "BTC";
    public static final String BCH_CURRENCY = "BCH";
    public static final String BSV_CURRENCY = "BSV";
    public static final BigDecimal MAX_ANTPOOL_PPS_GAP = new BigDecimal("0.001");
    public static final String CHINA_SOUTH = "chinaSouth";
    public static final String CHINA_NORTH = "chinaNorth";
    public static final String USA = "usa";
    public static final String EUROPE = "europe";
    public static final String API_CHINA_SOUTH = "hz";
    public static final String API_CHINA_NORTH = "huhehaote";
    public static final String API_EUROPE = "eu";
    //api方式获取数据
    public static final String API_DATA_TYPE = "btcComApi";
    //自研矿池数据库
    public static final String BINANCE = "binance";
    public static final String BTCCOM_BTC_CURRENCY = "btc";
    public static final String BTCCOM_BCH_CURRENCY = "bch";
    public static final String BTCCOM_BSV_CURRENCY = "bsv";
    public static final List<String> allCurrency = Lists.newArrayList(BTCCOM_BTC_CURRENCY, BTCCOM_BCH_CURRENCY, BTCCOM_BSV_CURRENCY);
    public static final long DEFAULT_GROUP_ID = -1l;
    public static final long ALL_GROUP = -2l;
    public static final Integer KLNIE_HOUR = 24;
    public static final Integer KLNIE_DAY = 30;
    public static final String CACHE_SPLIT = "-";
    public static final Long DELETE_WORKER_GROUP = 0l;
    //24分钟未提交算力为失效
    public static final long INVALID_WORKER_MINUTE = 24 * 60;
    //10分钟未提交算力为不活跃
    public static final long OFFLINE_WORKER_MINUTE = 15;
    //默认分页大小
    public static final int PAGE_SIZE = 3000;
    //日平均算力选24小时平均
    public static final int AVG_DAY_HOUR_HASH = 24;
    public static final BigDecimal AVG_DAY_HOUR = new BigDecimal("24");
    //默认分发大小
    public static final int DIVIDEND_SIZE = 1000;
    /**
     * 发送时间间隔
     */
    public static final long SNED_TIME_TNTERVAL = 1000 * 60 * 30;
    /**
     * 默认FPPS费率
     */
    public static final BigDecimal DEFAULT_FPPS_RATE = new BigDecimal("0.025");
    /**
     * 默认PPS费率
     */
    public static final BigDecimal DEFAULT_PPS_RATE = new BigDecimal("0.04");
    /**
     * 联合挖矿发放给用户的总金额
     */
    public static final BigDecimal UNION_MINER_USER_GIVE = new BigDecimal("0.96");
    /**
     * 矿工页面数据redis订阅频道
     */
    public static final String MINER_WORKER_CHANNEL = "MINER_WORKER_CHANNEL";
    /**
     * 统计页面数据redis订阅频道
     */
    public static final String STATISTIC_CHANNEL = "STATISTIC_CHANNEL";
    /**
     * 主页页面数据redis订阅频道
     */
    public static final String INDEX_CHANNEL = "INDEX_CHANNEL";
    /**
     * 用户算力数据redis订阅频道
     */
    public static final String USER_HASHRATE_CHANNEL = "USER_HASHRATE_CHANNEL";
    public static final String WSS_TOKEN_PREFIX = "PLNT";
    public static final String ENCODED_UTF8 = "UTF-8";
    public static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    /**
     * 发送时间间隔
     */
    public static final long SEND_TIME_INTERVAL = 1000 * 60 * 30;
    /**
     * 难度的取值的数量
     */
    public static final int blockInfoDiffLimit = 432;
    /**
     * btc的手续费（单字节的费用）
     */
    public static final Long BYTE_FEE = 30L;
    /**
     * 最大手续费交易费
     */
    public static final Long MAX_BYTE_FEE = 800l;
    /**
     * btc 未交易的数量
     */
    public static final Integer BTC_POOL_SIZE = 4000;
    /**
     * btc 存储手续费的数量
     */
    public static final Integer BTC_POOL_FEE_LIMIT_SIZE = 5;
    public static final String TOPIC_POOL_SAVINGS_EARN_INTERESTS = "TOPIC_POOL_SAVINGS_EARN_INTERESTS";
    /**
     * btc的基础单位
     */
    public static final BigDecimal BASE_UNIT = new BigDecimal(10).pow(8);
    /**
     * 图表的取之数量
     */
    public static final int chartDataLimit = 30;
    public static final Map<String, String> regionDataTypeMap = Maps.newHashMap();
    public static BigDecimal satoshi = new BigDecimal("0.00000001");

    static {
        regionDataTypeMap.put(API_CHINA_NORTH, API_DATA_TYPE);
        regionDataTypeMap.put(API_CHINA_SOUTH, API_DATA_TYPE);
        regionDataTypeMap.put(API_EUROPE, API_DATA_TYPE);

        regionDataTypeMap.put(CHINA_NORTH, BINANCE);
        regionDataTypeMap.put(CHINA_SOUTH, BINANCE);
        regionDataTypeMap.put(USA, BINANCE);
        regionDataTypeMap.put(EUROPE, BINANCE);
    }

    public static String getDataTypeByRegion(String region) {
        return regionDataTypeMap.get(region);
    }
}
