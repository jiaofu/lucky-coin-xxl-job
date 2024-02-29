package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pool_user")
public class PoolUserBean extends BaseBean {

    private Long uidBinance;// '币安的uid',
    private String poolUsername;//  '名称(全局唯一)',
    private String remark;//  '备注',
    private Integer miningBaoStatus; //是否开启矿池宝: 0 关闭,1 开启
    private Integer status;// '0 正常',
    @Deprecated //since v1.1
    private String puidDatabase;//32日内有提交算力的数据源

}

