package com.lucky.coin.service.util;

public class RedisKeyUtil {
    public static final String NORMAL_TWO_STR = "%s_%s";
    /**
     * puid和poolUsername对应关系
     */
    public static final String PUID_NAME_MAP = "PUID_NAME_MAP";
    /**
     * puid和poolUsername对应关系，iterm 为puid
     */
    public static final String PUID_NAME_MAP_KEY = "PUID_NAME_MAP_KEY_%s";
    /**
     * 用户算力
     */
    public static final String BINANCE_USER_HASHRARE = "BINANCE_USER_HASHRARE_";

    /**
     * 首页缓存
     */
    public static final String BOOL_MAIN_INDEX = "BOOL_INDEX";


    /**
     * 统计页 缓存
     */
    public static final String BOOL_STA_INDEX = "BOOL_STA_INDEX_";

    /**
     * 分组信息缓存Map
     */
    public static final String GROUP_CACHE_MAP = "GROUP_CACHE_MAP";
    /**
     * 状态信息缓存Map
     */
    public static final String STATUS_CACHE_MAP = "STATUS_CACHE_MAP";
    /**
     * 矿工信息缓存Map
     */
    public static final String WORKER_CACHE_MAP = "WORKER_CACHE_MAP";
    /**
     * 矿工信息缓存Map key
     */
    public static final String HASHRATE_CACHE_MAP_KEY = "HASHRATE_CACHE_MAP_KEY_%s_%s";

    /**
     * 算法的redis
     */
    public static final String ALGO_LIST = "ALGO_LIST";

    /**
     * symbol的redis
     */
    public static final String COIN_LIST = "COIN_LIST";

    /**
     * symbol和算法的的redis
     */
    public static final String ALGO_COIN_LIST = "ALGO_COIN_LIST";

    /**
     * 报警配置信息
     */
    public static final String WARING_CONFIG_INFO = "WARING_CONFIG_INFO";
    /**
     * 网络矿工占收益百分比
     */
    public static final String NET_FEE_RATE = "NET_FEE_RATE_%s_%s";
    /**
     * wss连接 key 下划线分割，中间是puid，最后是algoId
     */
    public static final String POOL_LISTEN_KEN_KEY = Constants.WSS_TOKEN_PREFIX + "_%s_%s";
    /**
     * 保存用户上次查询的矿机workerId的 key
     */
    public static final String WORKERS_PUSH_KEY = "WORKERS_PUSH_KEY_%s_%s";

    /**
     * 保存用户矿机的分组id
     */
    public static final String WORKER_GROUP_KEY = "WORKER_GROUP_KEY_%s_%s";
    /**
     * worker的kline数据
     */
    public static final String WORKER_KLINE_KEY = "WORKER_KLINE_KEY_%s_%s";
    /**
     * uidBinance 和 uidPoolBinance的对应关系
     */
    public static final String POOL_BINANCE_KEY = "POOL_BINANCE_KEY_%s";

    /**
     * 发送消息
     */
    public static final String DIVIDEND_BILL_LUCKY_BOT = "DIVIDEND_BILL_LUCKY_BOT_";
    /**
     * 昨日收益
     */
    public static final String STAT_YESTERDAY_PROFIT = "STAT_YESTERDAY_PROFIT";


    /**
     * 今日收益
     */
    public static final String STAT_TODAY_PROFIT = "STAT_TODAY_PROFIT";
    /**
     * 节点矿工列表redis
     */

    public static final String POOL_WORKER_LIST_KEY_PREFIX = "POOL_WORKER_LIST_KEY_";
    public static final String POOL_WORKER_LIST_KEY = POOL_WORKER_LIST_KEY_PREFIX + "%s";
    /**
     * 节点币种下矿工列表
     */
    public static final String WORKER_HASH_CACHE_KEY = "WORKER_HASH_CACHE_KEY_%s";
    /**
     * 矿工每日历史曲线
     */
    public static final String WORKER_DAY_HASH_KEY = "WORKER_DAY_HASH_KEY_%s_%s";
    //当日数据是否计算完毕的标志
    public static final String WORKER_DAY_FLAG_KEY = "WORKER_DAY_FLAG_KEY_%s_%s";
    public static final String WORKER_DAY_ITEM_KEY_PREFIX = "WORKER_DAY_ITEM_KEY_%s_";
    public static final String WORKER_DAY_ITEM_KEY = WORKER_DAY_ITEM_KEY_PREFIX + "%s";
    /**
     * 矿工小时历史曲线
     */
    public static final String WORKER_HOUR_HASH_KEY = "WORKER_HOUR_HASH_KEY_%s_%s";
    public static final String WORKER_HOUR_FLAG_KEY = "WORKER_HOUR_FLAG_KEY_%s_%s";
    public static final String WORKER_HOUR_ITEM_KEY_PREFIX = "WORKER_HOUR_ITEM_KEY_%s_";
    public static final String WORKER_HOUR_ITEM_KEY = WORKER_HOUR_ITEM_KEY_PREFIX + "%s";
    /**
     * 更新数据的时间
     */
    public static final String POOL_WORKER_LAST_TIME = "POOL_WORKER_LAST_TIME";
    /**
     * 矿池总活跃矿工数量
     */
    public static final String WORKER_POOL_VALID_KEY = "WORKER_POOL_VALID_KEY_%s";
    /**
     * 当日账单是否加载完毕
     */
    public static final String EARN_OVER_KEY = "EARN_OVER_KEY_%s_%s";
    public static final String EARN_DATA_KEY = "EARN_DATA_KEY_%s_%s";

    /**
     * 多节点合并后的天数据
     */
    public static final String CURRENCY_DAY_KEY = "CURRENCY_DAY_KEY_%s_%s";
    public static final String CURRENCY_HOUR_KEY = "CURRENCY_HOUR_KEY_%s_%s";
    /**
     * 矿池活跃矿工数量
     */
    public static final String POOL_TOTAL_VALID_WORKER = "POOL_TOTAL_VALID_WORKER";
    public static final String MARKET_PRICE_KEY = "MARKET_PRICE_KEY";
    /**
     * 第三方需要的机枪池数据是否获取完毕
     */
    public static final String GUN_EARN_KEY = "GUN_EARN_KEY_%s";
    /**
     * 第三方的机枪池增强收益数据是否获取完毕
     */
    public static final String SMART_EARN_FLAG_KEY = "SMART_EARN_FLAG_KEY_%s";
    /**
     * 机枪池增强率 day为key
     */
    public static final String ENHANCE_RATE_KEY = "ENHANCE_RATE_KEY";

    /**
     * _currency 爆块币种已经保存的最大高度
     */
    public static final String MAX_HEIGHT_KEY = "MAX_HEIGHT_KEY_%s";


    //public static final String DIVIDEND_BILL = "DIVIDEND_BILL_";

    /**
     * 获取利息日志
     */
    public static final String POOL_SAVINGS_DAY = "POOL_SAVINGS_DAY";
    /**
     * _currency 爆块信息
     * map key: height
     * value: block data
     */
    public static final String BLOCK_DATA_KEY = "BLOCK_DATA_KEY_%s";

    /**
     * 发送全部完成的消息
     */
    public static final String SEND_END_ALL_ALGO_BILL_MSG = "SEND_END_ALL_ALGO_BILL_MSG_";

    /**
     * 给相关用户发送信息
     */
    public static final String SEND_BOT_DAILY_MSG = "SEND_BOT_DAILY_MSG_";

    public static final String WARNING_ALGO_HASH_VALID = "WARNING_ALGO_HASH_VALID";

    /**
     * 是否全部查询完支付单，并支付订单状态
     */
    public static final String POOL_ADDRESS_PAYMENT_BILL_ID_STATUS = "POOL_ADDRESS_PAYMENT_BILL_ID_STATUS_";

    public static final String BLOCK_CHAIR_REQUEST_COST = "BLOCK_CHAIR_REQUEST_COST_%s";


    public static final String BILL_SEND_DAY_COIN_AMOUNT = "BILL_SEND_DAY_COIN_AMOUNT_%s_%s";

    public static final String BILL_SEND_DAY_ADDRESS_COIN_AMOUNT = "BILL_SEND_DAY_ADDRESS_COIN_AMOUNT_%s_%s";

    public static final String BILL_SEND_DAY_ADDRESS_COIN_FIRST = "BILL_SEND_DAY_ADDRESS_COIN_FIRST_%s_%s";

    public static final String BILL_SEND_DAY_ADDRESS_COIN_ERROR = "BILL_SEND_DAY_ADDRESS_COIN_ERROR_%s_%s";


    public static final String BILL_SEND_DAY_ADDRESS_COIN_COMPLETE = "BILL_SEND_DAY_ADDRESS_COIN_COMPLETE_%S_%S";


    public static final String SEND_BROADCAST_FAIL_ID = "SEND_BROADCAST_FAIL_ID_%S";


    /**
     * nkp地址余额
     */
    public static final String NKP_ADDRESS_BALANCE_NOTICE = "NKP_ADDRESS_BALANCE_NOTICE_%S_%S";

    /**
     * 每周打款
     */
    public static final String WEEK_SERVICE_CHARGE_FEE = "WEEK_SERVICE_CHARGE_FEE";


    /**
     * 获取btc的手续费集合
     */
    // public static final String BTC_FEE_BYTE_LIST ="BTC_FEE_BYTE_LIST";
    /**
     * 获取当前的手续费
     */
    public static final String BTC_FEE_BYTE_KVB = "BTC_FEE_BYTE_KVB";

    /**
     * 供应商的信息发送
     */
    public static final String PARTNER_ADDRESS_SEND_MSG_DAY = "PARTNER_ADDRESS_SEND_DAY_";

    public static String getPuidNameMapKey(Long puid) {
        return String.format(PUID_NAME_MAP_KEY, String.valueOf(puid));
    }

    /**
     * 返回矿工信息缓存Map key
     *
     * @param puid
     * @param algoId
     * @return
     */
    public static String getHashrateCacheMapKey(Long puid, Long algoId) {
        return String.format(HASHRATE_CACHE_MAP_KEY, String.valueOf(puid), String.valueOf(algoId));
    }


    public static String getListenKey(Long puid, Long algoId) {
        return String.format(POOL_LISTEN_KEN_KEY, String.valueOf(puid), String.valueOf(algoId));
    }

    public static String getWorkersPushKey(Long puid, Long algoId) {
        return String.format(WORKERS_PUSH_KEY, String.valueOf(puid), String.valueOf(algoId));
    }


    public static String getNetFeeRate(Long day, String currency) {
        return String.format(NET_FEE_RATE, String.valueOf(day), currency);
    }

    public static String getWorkerGroupKey(Long puid, String workerId) {
        return String.format(WORKER_GROUP_KEY, String.valueOf(puid), String.valueOf(workerId));
    }

    public static String getKlineKey(Long puid, Long algoId) {
        return String.format(WORKER_KLINE_KEY, String.valueOf(puid), String.valueOf(algoId));
    }

    public static String getPoolBinanceKey(Long puid) {
        return String.format(POOL_BINANCE_KEY, String.valueOf(puid));

    }

    public static String getWorkerListCacheKey(String user) {
        return String.format(POOL_WORKER_LIST_KEY, user);
    }

    public static String getWorkerHashCacheKey(String currency) {
        return String.format(WORKER_HASH_CACHE_KEY, currency);
    }

    public static String getWorkerDayHashCacheKey(String currency, Long day) {
        return String.format(WORKER_DAY_HASH_KEY, currency, String.valueOf(day));

    }

    public static String getWorkerDayHashItemKey(String currency, String puidName) {
        return String.format(WORKER_DAY_ITEM_KEY, currency, puidName);

    }

    public static String getWorkerHourHashCacheKey(String currency, Long hour) {
        return String.format(WORKER_HOUR_HASH_KEY, currency, String.valueOf(hour));

    }

    public static String getWorkerHourHashItemKey(String currency, String puidName) {
        return String.format(WORKER_HOUR_ITEM_KEY, currency, puidName);

    }

    public static String getWorkerDayFlagKey(String currency, Long day) {
        return String.format(WORKER_DAY_FLAG_KEY, currency, String.valueOf(day));
    }

    public static String getWorkerHourFlagKey(String currency, Long hour) {
        return String.format(WORKER_HOUR_FLAG_KEY, currency, String.valueOf(hour));
    }

    public static String getValidWorker(String currency) {
        return String.format(WORKER_POOL_VALID_KEY, currency);
    }

    public static String getEarnOverKey(String currency, Long day) {
        return String.format(EARN_OVER_KEY, currency, String.valueOf(day));
    }

    public static String getEarnDataKey(String currency, Long day) {
        return String.format(EARN_DATA_KEY, currency, String.valueOf(day));
    }

    public static String getCurrencyDayKey(String currency, Long day) {
        return String.format(CURRENCY_DAY_KEY, currency, String.valueOf(day));
    }

    public static String getCurrencyHourKey(String currency, Long day) {
        return String.format(CURRENCY_HOUR_KEY, currency, String.valueOf(day));
    }

    public static String getProductPriceKey(String product, Long day) {
        return String.format(NORMAL_TWO_STR, product, String.valueOf(day));
    }

    public static String getGunEarnKey(Long day) {
        return String.format(GUN_EARN_KEY, String.valueOf(day));
    }

    public static String getSmartEarnFlagKey(Long day) {
        return String.format(SMART_EARN_FLAG_KEY, String.valueOf(day));

    }

    public static String getMaxHeightKey(String currency) {
        return String.format(MAX_HEIGHT_KEY, currency);
    }

    public static String getBlockDataKey(String currency) {
        return String.format(BLOCK_DATA_KEY, currency);
    }

    public static String getBlockChairRequestCostKey(String day) {
        return String.format(BLOCK_CHAIR_REQUEST_COST, day);
    }

    public static String getBillSendDayAddressCoinAmount(Long day, String coinName) {
        return String.format(BILL_SEND_DAY_ADDRESS_COIN_AMOUNT, day, coinName);
    }

    public static String getBillSendDayAddressCoinFirst(Long day, String coinName) {
        return String.format(BILL_SEND_DAY_ADDRESS_COIN_FIRST, day, coinName);
    }

    public static String getBillSendDayAddressCoinError(Long day, String coinName) {
        return String.format(BILL_SEND_DAY_ADDRESS_COIN_ERROR, day, coinName);
    }

    public static String geBillSendDayAddressCoinComplete(Long day, String coinName) {
        return String.format(BILL_SEND_DAY_ADDRESS_COIN_COMPLETE, day, coinName);
    }

    public static String geSendBroadcastFailMsgKey(Long id) {
        return String.format(SEND_BROADCAST_FAIL_ID, id);
    }
}
