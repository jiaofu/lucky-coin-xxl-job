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

/**
 * Created by yyh on 2022/4/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pool_user_bill")
public class PoolUserBillSimpleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long puid;//'puid',
    private Long uidPoolBinance;// uid 中矿池钱包的用户
    private Long algoId;// '算法id（算力的算法）',
    private Long coinId;// '币种id(真正发给用户的币种)',
    private Integer type;// '0:挖矿 1:联合挖矿 2:活动奖励',
    private Long day;// '账单日期',
    private BigDecimal serviceChargeFee;//手续费支出 ',
    private BigDecimal userPayment;// 实际打款数量 ',
}
