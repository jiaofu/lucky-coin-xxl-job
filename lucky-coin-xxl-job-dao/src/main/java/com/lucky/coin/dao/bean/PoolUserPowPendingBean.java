package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 待处理中间表
 * status=0的记录最终要发多少币，发什么币
 * Created by yyh on 2020/3/17
 */
@Data
@Table(name = "pool_user_pow_pending")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoolUserPowPendingBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long puid;
    private Long uidPoolBinance;// uid 中矿池钱包的用户
    /**
     * 结算日期
     */
    private Long day;
    /**
     * 最终发放的币种
     */
    private Long coinId;
    /**
     * 原挖矿币种id
     */
    private Long originCoinId;
    /**
     * 挖矿算法Id
     */
    private Long algoId;
    /**
     * -1仅记录用，不是要发的币 0：未处理 1：已处理， 点击确定把今天的记录改为已处理
     */
    private Integer status;
    /**
     * 0挖矿收益，1联合挖矿收益, 2奖励 3返点，
     */
    private Integer type;
    /**
     * 联合挖矿的地址打款
     */
    private String address;
    /**该算法下币种转换后的earn*/
    /**
     * 结算数量 earn 单位为聪 已经计算了各个币种的网络手续费，未去除手续费
     */
    private BigDecimal earn;
    /**
     * 各节点的earn记录
     */
    private String dsEarn;
    /**
     * 平均网络手续费率 计算fpps公式因子 暂时只有btc的
     */
    private BigDecimal netFeeRate;
    /**
     * 挖矿手续费率
     */
    private BigDecimal minerFeeRate;
    /**
     * 挖矿时amount-earn多出来的就是矿工手续费
     * 有收益转让时，amount记录该账户能收到的钱
     */
    private BigDecimal amount;

    /**
     * 1， 联合挖矿时，用户算法总理论收益 大于0的才记录
     * 2， 挖矿时记录的未收益转让的挖矿收益，含矿工手续费
     */
    private BigDecimal unionCoinUserEarn;
    /**
     * 联合挖矿时，矿池算法总理论收益
     */
    private BigDecimal unionCoinPoolEarn;
    /**
     * 联合挖矿时， 该币种奖励的总数量
     */
    private BigDecimal unionCoinAmount;

    /**
     * 用于计算奖励的矿池 算法总算力 v1.1修改为算力，不再是accept
     */
    private BigDecimal algoPoolAccept;
    /**
     * v1.1修改为算力，不再是accept
     */
    private BigDecimal shareAccept;
    @Deprecated
    private BigDecimal shareStale;
    @Deprecated
    private BigDecimal shareReject;
    private BigDecimal difficulty;
    /**
     * 转成最终发的币的价格
     */
    private BigDecimal tickerPrice;

    private Date createdDate;
    private Date modifyDate;

    //puid,coinId,day,type索引;
}
