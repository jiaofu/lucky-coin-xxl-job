package com.lucky.coin.dao.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_coin")
public class PoolCoinBean extends BaseBean {


    private String name; // '币种,  //币种全称:bitcoin'
    private String sign;//'描述符号',
    private String symbol;//名称 //币种名称缩写:btc
    private Integer scale; //'小数点',
    private Integer status;  // '0:使用 1:停止',
    private Integer coinType; //0:挖矿币种 1:联合挖矿币种
    private Long unionAlgoId;//联合挖矿对应算法。
    private Integer poolIndex;//排序字段
    private String coinDatabase;// 数据库名字
    private Integer settleType; //  0:只能内部结算，1:只能外部地址结算，2:同时可以内外部结算'


    @JSONField(serialize = false)
    public Boolean getSupportSymbol() {
        return !this.symbol.equalsIgnoreCase("BSV");
    }

}
