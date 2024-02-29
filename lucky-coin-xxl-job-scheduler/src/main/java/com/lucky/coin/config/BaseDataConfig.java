package com.lucky.coin.config;

import com.alibaba.fastjson.parser.ParserConfig;

import com.lucky.coin.service.config.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class BaseDataConfig implements ApplicationRunner {

    @Autowired
    public SysConfig sysConfig;


    public void initData() {

        try {
            log.info(" BaseDataConfig 获取数据 ");
            ParserConfig.getGlobalInstance().setSafeMode(true);



        } catch (Exception ex) {
            log.error(" BaseDataConfig 出现异常", ex);
        }

    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        initData();

    }

}