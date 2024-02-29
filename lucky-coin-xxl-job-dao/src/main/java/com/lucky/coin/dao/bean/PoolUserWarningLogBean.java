package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

//警报日志

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_user_warning_log")
public class PoolUserWarningLogBean extends BaseBean {
    private Long puid;// 'puid',
    private Long warningConfigId;// 配置id
    private Long userWarningId;// 联系人id
    private BigDecimal userHashrate;// ' 算力 ',
    private Long userActive;//  '活跃矿工 ',
    private Integer status;// '0:未发送 1:发送',
    private Integer warningWay;//  '0:phone 1:email 2 phone&email',
    private Date warningDate;//  '报警时间',

}
