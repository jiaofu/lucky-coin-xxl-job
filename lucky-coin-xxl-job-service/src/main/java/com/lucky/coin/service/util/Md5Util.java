package com.lucky.coin.service.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;


@Slf4j
public class Md5Util {

    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static String hmacSha256(String message, String secret) {
        String hash = "";

        try {
            if (StringUtils.isEmpty(secret)) {
                secret = "secret";
            }


            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            hash = org.apache.commons.codec.binary.Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));

        } catch (Exception e) {
            log.error(" HmacSha256出现问题", e);
        }
        return hash;
    }

    public static Optional<String> hashPassword(String password, String salt) {

        long start = System.currentTimeMillis();
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            Optional optional = Optional.of(Base64.getEncoder().encodeToString(securePassword));
            long end = System.currentTimeMillis();
            log.info(" 执行耗时:" + (end - start));
            return optional;

        } catch (Exception ex) {
            log.error("Exception encountered in hashPassword()", ex);
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }


    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String password = "poolnusd" + i;
            hashPassword(password, "pbgkeymd5").get();
        }

    }


}

