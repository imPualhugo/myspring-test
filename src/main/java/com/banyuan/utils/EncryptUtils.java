package com.banyuan.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密类,可以加密字符串,并转为base64编码
 * 为了安全和容量起见,不建议将SHA加密后的字节转化为16位数字字符
 *
 * @author hyz
 */
public final class EncryptUtils {
    private EncryptUtils() {
    }

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";
    public static final String SHA512 = "SHA-512";

    /**
     * SHA算法函数,接收将一个字符串转化为base64型的SHA字符串
     *
     * @param input     输入字符串
     * @param algorithm 算法格式,支持MD2 MD5 SHA-1 SHA-256 SHA-384 SHA-512
     * @return base64编码的信息摘要字符串
     */
    public static String sha(String input, String algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            byte[] bytes = messageDigest.digest(input.getBytes());

            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5加密字符串的调用方法
     * @param str 需要加密字符串
     * @return 使用MD5加密后的base64字符串
     */
    public static String md5(String str) {
        return sha(str, MD5);
    }


    /**
     * SHA-256加密字符串的调用方法
     * @param str 需要加密字符串
     * @return 使用SHA-256加密后的base64字符串
     */
    public static String sha256(String str) {
        return sha(str, SHA256);
    }

    /**
     * SHA-512加密字符串的调用方法
     * @param str 需要加密字符串
     * @return 使用SHA-512加密后的base64字符串
     */
    public static String sha512(String str) {
        return sha(str, SHA512);
    }

    /**
     * 直接通过sha512生成一个base64编码的加密字符串
     * @param input
     * @return
     */
    public static String encrypt(String input){
        return sha512(input);
    }

    /**
     *
     * @param fis
     * @param algorithm
     * @return
     */
    public static String sha(FileInputStream fis,String algorithm){

        byte[] bytes = new byte[4096];
        int len;
        try(BufferedInputStream bis = new BufferedInputStream(fis)){
            MessageDigest md = MessageDigest.getInstance(algorithm);
            while ((len = bis.read(bytes))!=-1){
                md.update(bytes,0,len);
            }
            bytes = md.digest();
            return Base64.getEncoder().encodeToString(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        System.out.println(sha("123","SHA-512"));
//    }



}
