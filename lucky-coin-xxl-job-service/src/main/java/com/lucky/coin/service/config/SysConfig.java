package com.lucky.coin.service.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig {


    /**
     *   * 是否是测试环境 ，0 是测试环境 1 是线上环境
     */

    private int onlineTest;


    /**
     * market 的信息
     */
    private String xCmcProApiKey;

}
