package com.lucky.coin.config;

import com.lucky.coin.service.config.XxlJobAwsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class XxlJobAwsConfig {


    @Bean
    @ConfigurationProperties(prefix = "xxl.job")
    public XxlJobAwsVo getAccessToken() throws Exception {


        return new XxlJobAwsVo();
    }
}
