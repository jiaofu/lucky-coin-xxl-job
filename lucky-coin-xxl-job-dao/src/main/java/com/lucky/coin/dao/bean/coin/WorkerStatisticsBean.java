package com.lucky.coin.dao.bean.coin;

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

/**
 * 依赖redis里的统计数据保存的每小时矿工信息
 * <p>
 * Created by yyh on 2020/3/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worker_statistics_record")
public class WorkerStatisticsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long puid;
    private Long algoId;
    /**
     * 0表示该用户该算法所有矿机的情况
     */
    private String workerId;
    /**
     * 形如2020052811
     */
    private Long minute;
    /**
     * 实时15min算力
     */
    private BigDecimal hashRate;
    /**
     * 实时15min拒绝率
     */
    private Double rejectRate;
    /**
     * 日均算力
     */
    private BigDecimal dayHashRate;
//    /**日均拒绝率*/
//    private Double dayRejectRate;

    /**
     * 矿工数量
     */
    private Integer totalNum;
    /**
     * 有效矿工数量
     */
    private Integer validNum;
    /**
     * 失效矿工数量
     */
    private Integer invalidNum;

    private Date createdDate;// '创建时间',
    private Date modifyDate; //修改时间',
}
