package com.lucky.coin.service.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2020/3/11
 */
public class Constants {

    public static Double defaultFactor = new Double(0.25);

    public static Long startDay = 20231219L;


    /**
     * 开始没有被选中，那么是第三区的观察区
     */
    public static Integer classPart =3;

    public static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

}
