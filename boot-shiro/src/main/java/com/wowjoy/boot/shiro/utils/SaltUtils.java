package com.wowjoy.boot.shiro.utils;

import java.util.Random;

/**
 * @author scq
 * @date 2020-08-07 11:07:00
 */
public class SaltUtils {
    /**
     * 生成salt
     * @param length
     * @return
     */
    public static String getSalt(int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0987654321!@#$%^&*()_+=".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char achar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(achar);
        }
        return stringBuilder.toString();
    }
}
