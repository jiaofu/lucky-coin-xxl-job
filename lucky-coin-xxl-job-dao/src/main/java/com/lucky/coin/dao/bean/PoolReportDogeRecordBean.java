package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
@Table(name = "pool_report_doge_record")
@AllArgsConstructor
@NoArgsConstructor
public class PoolReportDogeRecordBean {

    private Long id;//,
    private Long day;// '账单日期',
    private String coinName;//  '币种名称缩写:btc',
    private Long recordId;//  '账单日期',
    private Integer output;//  '爆块数量',
    private BigDecimal outputIncome;// '爆块金额',
    private BigDecimal innerFee;//  '系统收取的手续费',
    private BigDecimal theoryExpenditure;//  '分发给用户打款',
    private Date createdDate;
    private Date modifyDate; //修改时间',
}
