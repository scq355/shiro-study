package com.wowjoy.shiro.encryption;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author scq
 * @date 2020-08-06 09:06:00
 */
public class MyMD5 {
    public static void main(String[] args) {
        Md5Hash md5Hash1 = new Md5Hash();
        md5Hash1.setBytes("scq355".getBytes());
        String toHex = md5Hash1.toHex();
        System.out.println(toHex);

        Md5Hash md5Hash = new Md5Hash("scq355");
        System.out.println(md5Hash.toHex());

        Md5Hash md5Hash2 = new Md5Hash("scq355", "OlD*(n3");
        System.out.println(md5Hash2);

        Md5Hash md5Hash3 = new Md5Hash("scq355", "OlD*(n3", 1024);
        System.out.println(md5Hash3);
    }
}
