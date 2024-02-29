package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by yyh on 2020/3/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_user_pow_pending_log")
@Builder
public class PoolUserPowPendingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long day;
    //ProfitTypeEnum 没有返点的 联合挖矿和奖励使用同一个
//    private Integer type;
    private Long algoId;
    /**
     * 0：正在计算，1：计算完毕
     */
    private Integer status;
    private Date createdDate;
    private Date modifyDate;

    //day type联合唯一索引
}
