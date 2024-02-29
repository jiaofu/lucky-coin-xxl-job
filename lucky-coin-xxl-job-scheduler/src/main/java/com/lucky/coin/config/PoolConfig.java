package com.lucky.coin.config;

import com.binance.pool.config.config.BlockChairKeyConfig;
import com.binance.pool.config.config.TokenViewKeyConfig;
import com.lucky.coin.service.config.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yyh on 2020/4/5
 */
@Configuration
@Slf4j
public class PoolConfig {


    @Bean
    @ConfigurationProperties(prefix = "pool")
    public SysConfig getSysConfig() {
        return SysConfig.builder()
                .build();
    }


    @Bean
    @ConfigurationProperties(prefix = "pool.blockchair")
    public BlockChairKeyConfig getBlockChairKeyConfig(@Autowired SysConfig sysConfig) {
        BlockChairKeyConfig blockChairKeyConfig = BlockChairKeyConfig.builder()

                .build();
        log.info("getBlockChairKeyConfig apiKey is isBlank:{}", StringUtils.isBlank(blockChairKeyConfig.getApi_key()));
        return blockChairKeyConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "pool.tokenview")
    public TokenViewKeyConfig getTokenViewKeyConfig(@Autowired SysConfig sysConfig) {
        TokenViewKeyConfig tokenViewKeyConfig = TokenViewKeyConfig.builder()

                .build();
        log.info("TokenViewKeyConfig apiKey is isBlank:{}", StringUtils.isBlank(tokenViewKeyConfig.getApikey()));
        return tokenViewKeyConfig;
    }


}
