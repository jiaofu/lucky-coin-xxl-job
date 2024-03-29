package com.lucky.coin.config;

import com.lucky.coin.config.bean.AwsDataSourceConfig;
import com.lucky.coin.service.config.SysConfig;
import com.lucky.coin.service.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Describe: DataSource Config
 */
@Configuration
@Slf4j
public class DataSourceConfig {


    @Autowired
    SysConfig sysConfig;

    /**
     * 配置数据源1
     *
     * @return
     */
    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource dataSource1() {
        AwsDataSourceConfig awsDataSourceConfig = new AwsDataSourceConfig();
        if (StringUtils.isNotBlank(awsDataSourceConfig.getHost())) {
            return DataSourceBuilder.create()
                    .username(awsDataSourceConfig.getUsername())
                    .password(awsDataSourceConfig.getPassword())
                    .url(awsDataSourceConfig.poolJdbcUrl())
                    .driverClassName(Constants.MYSQL_DRIVER_CLASS_NAME)
                    .build();
        } else {
            return DataSourceBuilder.create()
                    .driverClassName(Constants.MYSQL_DRIVER_CLASS_NAME)
                    .build();
        }
    }

    @Bean(name = "dbRead")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    @Primary
    public DataSource dataSource3() {
        AwsDataSourceConfig awsDataSourceConfig = new AwsDataSourceConfig();
        if (StringUtils.isNotBlank(awsDataSourceConfig.getHost())) {
            return DataSourceBuilder.create()
                    .username(awsDataSourceConfig.getUsername())
                    .password(awsDataSourceConfig.getPassword())
                    .url(awsDataSourceConfig.poolJdbcUrl())
                    .driverClassName(Constants.MYSQL_DRIVER_CLASS_NAME)
                    .build();
        } else {
            return DataSourceBuilder.create()
                    .driverClassName(Constants.MYSQL_DRIVER_CLASS_NAME)
                    .build();
        }
    }

    @Bean(name = "dynamicDS")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源,当没有指定的时候使用，可以当做主数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put("db1", dataSource1());
        dsMap.put("dbRead", dataSource3());
        // 注册多数据源
        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }
}
