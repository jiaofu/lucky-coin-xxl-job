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
 * Created by yyh on 2020/5/22
 */
@Data
@Builder
@Table(name = "pool_give_address")
@AllArgsConstructor
@NoArgsConstructor
public class PoolGiveAddressBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;// 'ubinance的id',
    private Long uidPoolBinance;// 'uid 中矿池钱包的用户',
    private Long coinId;//  '币种id',
    private Integer status;//  '0' COMMENT '0:使用 1:停止',
    private String poolAddress;//  '地址',
    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',

}
