package com.lucky.coin.dao.bean.coinstats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stats_workers_day")
public class StatsWorkersDayBean {

    private Long puid;
    private String workerId;
    private Long day;
    private Long shareAccept;
    private Long shareStale;
    private Long shareReject;
    //    private String rejectDetail;
    private Double rejectRate;
//    private BigDecimal score;
//    private BigDecimal earn;
//    private Date createdAt;
//    private Date updatedAt;

    @Transient
    private long hashRate;

    /**
     * 相同的(puid,worker)累加算力，updatedAt选最新的
     * 仅计算算力适用，score和earn不能用
     */
    public StatsWorkersDayBean add(StatsWorkersDayBean other) {
        if (other == null) {
            return this;
        }
        if (this.puid - other.getPuid() != 0
                || !StringUtils.equals(this.workerId, other.getWorkerId())
                || this.day - other.getDay() != 0) {
            throw new RuntimeException("StatsWorkersDayBean puid,workerId,hour not equal can not add ");
        }
        StatsWorkersDayBean result = StatsWorkersDayBean.builder()
                .puid(this.puid)
                .workerId(this.workerId)
                .day(this.day)
                .shareAccept(this.shareAccept + other.getShareAccept())
                .shareStale(this.shareStale + other.getShareStale())
                .shareReject(this.shareReject + other.getShareReject())
                .build();
        result.setRejectRate((double) ((result.shareReject + result.shareStale) / (result.shareReject + result.shareStale + result.shareAccept)));
        return result;
    }

    /*public static void main(String[] args) {
        List<StatsWorkersDayBean> dayData = Lists.newArrayList();
        dayData.add(StatsWorkersDayBean.builder()
                .puid(1l).workerId(2l).day(20200312l)
                .shareAccept(100).shareStale(1).shareReject(1l).rejectDetail("1;").updatedAt(new Date())
                .build());
        dayData.add(StatsWorkersDayBean.builder()
                .puid(1l).workerId(2l).day(20200312l)
                .shareAccept(100).shareStale(1).shareReject(1l).rejectDetail("1;").updatedAt(new Date())
                .build());
        dayData.add(StatsWorkersDayBean.builder()
                .puid(1l).workerId(2l).day(20200312l)
                .shareAccept(100).shareStale(1).shareReject(1l).rejectDetail("1;").updatedAt(new Date())
                .build());
        StatsWorkersDayBean dayMergeData = dayData.stream().reduce((hourData1,hourData2)-> hourData1.add(hourData2)).get();
        System.out.println(dayMergeData);

    }*/
}
