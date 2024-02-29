package com.lucky.coin.dao.bean.coinstats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stats_users_day")
public class StatsUsersDayBean {

    private Long puid;
    private Long day;

    private BigDecimal shareAccept;
    private BigDecimal shareStale;
    private BigDecimal shareReject;
    private String rejectDetail;
    private Double rejectRate;
    private BigDecimal score;
    private BigDecimal earn;
    private Date createdAt;
    private Date updatedAt;

    @Transient
    private Long coinId;
    @Transient
    private String dataSource;
    @Transient
    private String dsEarn;

    public StatsUsersDayBean add(StatsUsersDayBean other) {
        if (other == null) {
            return this;
        }
        if (this.puid - other.getPuid() != 0
                || this.day - other.getDay() != 0) {
            throw new RuntimeException("StatsUsersDayBean puid,day not equal can not add ");
        }
        StatsUsersDayBean result = StatsUsersDayBean.builder()
                .puid(this.puid)
                .day(this.day)
                .earn(this.getEarn().add(other.getEarn()))
                .score(this.getScore().add(other.getScore()))
                .coinId(this.coinId)
                .shareAccept(this.getShareAccept().add(other.getShareAccept()))
                .shareStale(this.getShareStale().add(other.getShareStale()))
                .shareReject(this.getShareReject().add(other.getShareReject()))
                .build();
        result.setRejectRate(calRejectRate(result.getShareAccept(), result.getShareStale(), result.getShareReject()));
        String dsEarn = "";
        if (StringUtils.isNotBlank(this.getDsEarn())) {
            dsEarn = dsEarn.concat(this.getDsEarn());
        }
        if (StringUtils.isNotBlank(other.getDsEarn())) {
            dsEarn = dsEarn.concat(other.getDsEarn());
        }
        if (StringUtils.isNotBlank(this.getDataSource())) {
            dsEarn = dsEarn.concat(this.dataSource.concat(":").concat(this.getEarn().stripTrailingZeros().toPlainString()).concat(","));
        }
        if (StringUtils.isNotBlank(other.getDataSource())) {
            dsEarn = dsEarn.concat(other.dataSource.concat(":").concat(other.getEarn().stripTrailingZeros().toPlainString()).concat(","));
        }
        result.setDsEarn(dsEarn);

        return result;
    }

    public StatsUsersDayBean dsAdd(StatsUsersDayBean other) {
        if (other == null) {
            return this;
        }
        if (!this.dataSource.equals(other.getDataSource())
                || this.day - other.getDay() != 0) {
            throw new RuntimeException("StatsUsersDayBean ds,day not equal can not dsAdd ");
        }
        StatsUsersDayBean result = StatsUsersDayBean.builder()
//                .puid(this.puid)
                .dataSource(this.getDataSource())
                .day(this.day)
                .earn(this.getEarn().add(other.getEarn()))
                .score(this.getScore().add(other.getScore()))
                .coinId(this.coinId)
                .shareAccept(this.getShareAccept().add(other.getShareAccept()))
                .shareStale(this.getShareStale().add(other.getShareStale()))
                .shareReject(this.getShareReject().add(other.getShareReject()))
                .build();
        return result;
    }

    private Double calRejectRate(BigDecimal shareAccept, BigDecimal shareStale, BigDecimal shareReject) {
        if (shareAccept.add(shareStale).add(shareReject).compareTo(BigDecimal.ZERO) == 0) {
            return 0d;
        }
        return (shareReject.add(shareStale)).divide(
                shareAccept.add(shareStale).add(shareReject), 12, RoundingMode.DOWN
        ).doubleValue();
    }

}
