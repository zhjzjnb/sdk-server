package com.zsdk.server.util;


import java.util.UUID;

public class SystemUtil {

    public static String generateAppKey() {
        return EncryptUtil.md5("" + System.nanoTime());
    }
    public static String generateAppSecret(){
        UUID uuid = UUID.randomUUID();
        return EncryptUtil.md5(uuid.toString());
    }

    public static String genUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String genPublicKey(){
        return "public key";
    }

    public static String genPrivateKey(){
        return "private key";
    }

    public static  void  main(String []args){
        System.out.println("xxx" + UUID.randomUUID());
        System.out.println(genUUID());
    }
}
