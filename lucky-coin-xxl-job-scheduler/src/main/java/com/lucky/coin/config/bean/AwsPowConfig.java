package com.lucky.coin.config.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yyh on 2020/4/5
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwsPowConfig {
    private String prikey;
}
