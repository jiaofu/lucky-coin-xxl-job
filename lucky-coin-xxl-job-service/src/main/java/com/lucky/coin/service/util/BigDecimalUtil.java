package com.lucky.coin.service.util;

import java.math.BigDecimal;

/**
 * Created by yyh on 2020/5/12
 */
public class BigDecimalUtil {
    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        if (num1 == null && num2 == null) {
            return BigDecimal.ZERO;
        }
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        return num1.add(num2);
    }

    public static BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        if (num1 == null && num2 == null) {
            return BigDecimal.ZERO;
        }
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        return num1.subtract(num2);
    }

    public static Long addLong(Long num1, Long num2) {
        if (num1 == null && num2 == null) {
            return 0l;
        }
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        return num1 + num2;
    }

    public static Integer addInteger(Integer num1, Integer num2) {
        if (num1 == null && num2 == null) {
            return 0;
        }
        if (num1 == null) {
            return num2;
        }
        if (num2 == null) {
            return num1;
        }
        return num1 + num2;
    }
}
