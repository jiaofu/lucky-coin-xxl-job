package com.lucky.coin.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pool_user_warning")
public class PoolUserWarningBean extends BaseBean {
    private Long puid;
    private Long algoId;
    private String poolUsername;
    private String userLocation;
    private String email;
    private String phone;
    private Integer status;// '0 正常',
}
