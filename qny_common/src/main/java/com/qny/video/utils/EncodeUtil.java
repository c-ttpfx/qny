package com.qny.video.utils;

import java.util.Base64;

/**
 * @author ttpfx
 * @since 2023/10/25
 */
public class EncodeUtil {

    public static String stringToBase64(String input) {
        // 使用Base64编码
        byte[] encoded = Base64.getEncoder().encode(input.getBytes());

        return new String(encoded);
    }

    public static void main(String[] args) {
        System.out.println(stringToBase64("qny-book"));
    }
}
