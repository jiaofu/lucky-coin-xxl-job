package com.lucky.coin.config;


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





}
