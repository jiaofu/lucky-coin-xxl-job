package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

//矿池费率表

@Data
@Table(name = "pool_user_rate")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoolUserRateBean extends BaseBean {

    private Long uidPoolBinance;// uid 中矿池钱包的用户
    private Long algoId;//'算法id',
    private BigDecimal ppsRate;// 'pps 费率 ',
    private BigDecimal fppsRate;// 'fpps 费率'
    private Integer status;// '0 正常',
    private Long rebatePoolBinance;// 返点用户
    private BigDecimal rebateRate;  // 返点比例
}
