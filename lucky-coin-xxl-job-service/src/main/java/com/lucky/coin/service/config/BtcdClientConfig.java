package com.lucky.coin.service.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BtcdClientConfig {
    /**
     * host
     */
    private String host;

    /**
     * port
     */
    private String port;

    /**
     * 账号
     */
    private String user;

    /**
     * 密码
     */
    private String password;
}