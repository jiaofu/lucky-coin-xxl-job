package com.lucky.coin.service.util;


import java.security.SecureRandom;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yyh
 * @date 2020/3/11
 */
public class StringUtil {
    public static final String SPECIAL_REGEX = "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
    public static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    public static final SecureRandom random = new SecureRandom();
    static Pattern p = Pattern.compile(SPECIAL_REGEX);

    public static boolean isSpecialChar(String str) {
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static Boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

    public static String getRandomToken() {
        byte bytes[] = new byte[20];
        try {
            random.nextBytes(bytes);
            return encoder.encodeToString(bytes).replaceAll("[-_]", "").substring(0, 15);
        } catch (Exception e) {
            random.nextBytes(bytes);
            return encoder.encodeToString(bytes).replaceAll("[-_]", "").substring(0, 15);
        }
    }

    //判断是不是整数
    public static boolean isNum(String key) {
        Pattern digit = Pattern.compile("\\d+");
        return digit.matcher(key).matches();
    }

    public static void main(String[] args) {
        System.out.println(isNum("23232"));
        System.out.println(isNum("23232.23"));
        System.out.println(isNum("0"));
    }

}
