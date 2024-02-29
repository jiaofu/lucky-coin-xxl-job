package com.lucky.coin.dao.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@Table(name = "pool_profit_transfer")
@AllArgsConstructor
@NoArgsConstructor
public class PoolProfitTransferBean extends BaseBean {

    private Long uid;// 'ubinance的id',
    private Long puid;//
    private Long transferPoolBinance;// 'uid 中矿池钱包的用户',
    private Long algoId;//  '算法id',
    private Integer status;//  '0' COMMENT '0:使用 1:停止',
    private BigDecimal rate;//  '比例',


}
