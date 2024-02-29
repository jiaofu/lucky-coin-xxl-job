package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_warning_config")
public class PoolWarningConfigBean extends BaseBean {

    /**
     * FLAG 位序1起第1位，算力警戒值
     */
    public static final int FLAG_HASHRATE = 1;
    /**
     * FLAG 位序1起第2位，设置活跃矿机警戒值
     */
    public static final int FLAG_WORKER = 1 << 1;
    /**
     * FLAG 位序1起第3位，算力波动
     */
    public static final int FLAG_WAVE = 1 << 2;
    private Long puid;// 'puid',
    private Long algoId;//算法id
    private Integer warningStatus;//  '是否开启算力报警: 0 关闭,1 开启',
    private BigDecimal warningHashrate;//  ' 警戒算力 ',
    private Integer warningWorkerNum;//  '警戒活跃矿工 ',
    private Integer type;  // 0 中文
    private Integer flag;  // '类型 1 位 算力境界值 2：设置活跃矿机警戒值 3：算力波动'
    private BigDecimal warningWaveValue;  // 算力波动值
    private BigDecimal warningWaveRate;  // 算力波动率

    public boolean hasAttr(long expectFlag) {
        // (A & B) == A ，说明，A的位图是B的位图的子集
        return (expectFlag & flag) == expectFlag;
    }

}
