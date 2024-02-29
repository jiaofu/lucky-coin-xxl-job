package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;


//pool_user_rebate

//用户返点比例表
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_user_rebate")
public class PoolUserRebateBean extends BaseBean {

    private Long puid;// 'puid （挖矿用户名）',
    private Long uidPoolBinance;// uid 中矿池钱包的用户
    private Long algoId;// 算法id ',
    private BigDecimal rebate;// '返点比例 ',
    private Integer status;// '0:使用 1:停止',

}
