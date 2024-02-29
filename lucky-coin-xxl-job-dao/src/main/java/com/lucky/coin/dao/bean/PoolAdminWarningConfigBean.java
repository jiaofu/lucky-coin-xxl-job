package com.lucky.coin.dao.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pool_admin_warning_config")
public class PoolAdminWarningConfigBean extends BaseBean {
    private Long uidPoolBinance;// '本人矿池Id',
    private Long puid;// '子账户id',
    private Long algoId;// '算法id',
    private String businessManager;//'商务管理员',
    private BigDecimal warningHashrate;// '算力警戒值',
    private BigDecimal theoryHashrate;// '理论算力',
    private String email;// '邮箱',
    private String phone;// '手机号',
    private Integer type;// '0中文 1 英文',
    private Integer status;// 0 正常 1 删除
    private String userLocation;// 用户的手机号 地区比如 86
    private String remarkName;
    private String remarkInformation;
}
