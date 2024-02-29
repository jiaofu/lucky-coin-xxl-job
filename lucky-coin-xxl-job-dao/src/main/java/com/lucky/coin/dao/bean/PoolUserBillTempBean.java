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
 * Created by yyh on 2020/3/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pool_user_bill_temp")
public class PoolUserBillTempBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long puid;//'puid',
    private Long uidPoolBinance;// uid 中矿池钱包的用户
    private Long algoId;// '算法id（算力的算法）',
    private Long coinId;// '币种id(真正发给用户的币种)',
    private Integer type;// '0:挖矿 1:联合挖矿 2:活动奖励',
    private BigDecimal shareAccept;// ' 平均总算力 ',
    private BigDecimal shareStale;// ' 平均总算力 ',
    private BigDecimal shareReject;// ' 平均总算力 ',

    private BigDecimal minerNetworkFee;// 矿工手续费 ',
    private BigDecimal serviceChargeFee;//手续费支出 ',
    private BigDecimal userPayment;// 实际打款数量 ',
    private Integer status;//'0:待支付， 1:支付中  2：已支付',
    private Long day;// '账单日期',

    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
