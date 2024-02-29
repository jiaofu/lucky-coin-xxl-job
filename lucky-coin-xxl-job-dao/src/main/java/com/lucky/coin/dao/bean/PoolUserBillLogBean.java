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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pool_user_bill_log")
public class PoolUserBillLogBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long billId;
    private Long puid;//'puid',
    private Long uidPoolBinance;//入账币安用户id
    private Long algoId;// '算法id（算力的算法）',
    private Long coinId;// '币种id(真正发给用户的币种)',
    private Integer type;// '0:挖矿 1:联合挖矿 2:活动奖励',

    private BigDecimal userPayment;// 实际打款数量 ',
    private Integer sendCount;// '请求次数',

    /**
     * 请求结果
     */
    private String resultMsg;// '请求次数',
    private Long day;// '账单日期',
    private String batchId;// '分发的流水号',
    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
