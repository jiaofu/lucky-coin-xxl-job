package com.lucky.coin.config.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwsUserMiningConfig {


    /**
     * db 的 密钥
     */
    private String dbSecretkey;

}