package com.zsdk.server.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * Created by zhj on 17/3/15.
 */
public class EncryptUtil {

    public static String genToken(String appId){
        return appId;
    }

    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    static String e = "9238513401340235";


    public static String bytesToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        if (src == null || src.length <= 0)
            return null;
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2)
                sb.append(0);
            sb.append(hv);
        }
        return sb.toString();
    }

    //将指定byte数组以16进制的形式打印到控制台
    public static void printHexString( byte[] b) {
//        for (int i = 0; i < b.length; i++) {
//            String hex = Integer.toHexString(b[i] & 0xFF);
//            if (hex.length() == 1) {
//                hex = '0' + hex;
//            }
//            System.out.print(hex.toUpperCase());
//        }
//        System.out.println();
    }


    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    // 加密
    public static String AESEncrypt(String src, String key) throws Exception {
        if (key == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (key.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"0102030405060708
        IvParameterSpec iv = new IvParameterSpec(e.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(src.getBytes());
        printHexString(encrypted);
        return bytesToHexString(encrypted);

    }

    // 解密
    public static String AESDecrypt(String src, String key) throws Exception {
        try {
            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (key.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(e.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = hexStringToBytes(src);

            printHexString(encrypted1);

            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("aaa:" + md5("aaa"));
        System.out.println("axxxaa:" + md5("axxxaa"));
        System.out.println("1:" + md5("1"));


        String key = UUID.randomUUID().toString().substring(0, 16);
        String src = "Email : wfung_kwok@xxx.com";
        System.out.println(src);
        // 加密
        long start = System.currentTimeMillis();
        String enString = AESEncrypt(src, key);
        System.out.println("加密后的字串是：" + enString);

        long useTime = System.currentTimeMillis() - start;
        System.out.println("加密耗时：" + useTime + "毫秒");

        // 解密
        start = System.currentTimeMillis();
        String DeString = AESDecrypt(enString, key);
        System.out.println("解密后的字串是：" + DeString);
        useTime = System.currentTimeMillis() - start;
        System.out.println("解密耗时：" + useTime + "毫秒");
    }


}
