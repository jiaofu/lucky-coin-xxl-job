CREATE database if NOT EXISTS `lucky_coin` default character set utf8mb4 collate utf8mb4_unicode_ci;
use `lucky_coin`;

SET NAMES utf8mb4;

DROP table coin_info;

CREATE TABLE `coin_info` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `day` int(11) NOT NULL COMMENT '日期',
                             `symbol` varchar(255) DEFAULT NULL COMMENT 'symbol',
                             `slug` varchar(255) DEFAULT NULL COMMENT 'slug',
                             `name` varchar(255) DEFAULT NULL COMMENT '币种名称',
                             `market_id` bigint(20) DEFAULT NULL COMMENT 'market Id',
                             `max_supply` bigint(20) NOT NULL DEFAULT '0.00000000' COMMENT '最大供应数量',
                             `total_supply` bigint(20) NOT NULL DEFAULT '0.00000000' COMMENT '当前流通数量',
                             `fully_diluted_market_cap` decimal(30,8) NOT NULL DEFAULT '0.00000000' COMMENT '总市值',
                             `coin_ranking` int(11) NOT NULL COMMENT '得分',
                             `db_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据库插入时间，请勿修改',
                             `db_modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据库更新时间，请勿修改',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `market_id` (`market_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


ALTER TABLE coin_info
    ADD market_cap_rank   int(11) NOT NULL COMMENT '市值排名' AFTER  coin_ranking;

ALTER TABLE coin_info
    ADD status   int(11) NOT NULL  default '0' COMMENT '状态 0使用，1使用' AFTER  symbol;
DROP table market_info;


CREATE TABLE `market_info` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `coin_id` int(11) NOT NULL COMMENT '币种Id',
                               `day` int(11) NOT NULL COMMENT '日期',
                               `symbol` varchar(255) DEFAULT NULL COMMENT '币种名称缩写:btc',
                               `price` decimal(30,8) NOT NULL DEFAULT '0.00000000' COMMENT '价格',
                               `percent_change_24h` decimal(30,8) NOT NULL DEFAULT '0.00000000' COMMENT '24小时价格',
                               `percent_change_7d` decimal(30,8) NOT NULL DEFAULT '0.00000000' COMMENT '7天价格变化',
                               `percent_change_30d` decimal(30,8) NOT NULL DEFAULT '0.00000000' COMMENT '30日安价格价格',
                               `coin_ranking` int(11) NOT NULL COMMENT '本次排名',
                               `coin_score`  bigint(20) NOT NULL COMMENT '本次得分',
                               `fully_diluted_market_cap` decimal(30,8) NOT NULL DEFAULT '0.00000000' COMMENT '总市值',
                               `last_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '行情更新时间',
                               `db_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据库插入时间，请勿修改',
                               `db_modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据库更新时间，请勿修改',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `day` (`day`,`coin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;