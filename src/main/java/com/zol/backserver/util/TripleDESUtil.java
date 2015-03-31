/**
 * 
 */
package com.zol.backserver.util;

/**
 * @author HuRenbing
 *
 */

import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


@SuppressWarnings("restriction")
public class TripleDESUtil {
    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";

    public static String decrypt(String s) throws Exception
    {
        
        final byte[] secretkey = generateSecretKey();
        final byte[] ivbytes = TripleDESUtil.randomIVBytes();
        byte[] data = StrConvertUtil.hexStrToByteArr(s);
        byte[] dec = decrypt(data, secretkey, ivbytes);
        return new String(dec,"UTF-8");
    }

    private static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, sec, IvParameters);
        return cipher.doFinal(data);
    }

    public static String encrypt(String s) throws Exception
    {
        final byte[] secretkey = generateSecretKey();
        final byte[] ivbytes = TripleDESUtil.randomIVBytes();
        
        byte[] data = s.getBytes();

        byte[] enc = encrypt(data, secretkey, ivbytes);
        return StrConvertUtil.byteArrToHexStr(enc);
    }

    private static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, sec, IvParameters);
        return cipher.doFinal(data);
    }

    private static String SecretKey = "61260dd315f280e3541fad83791f26f415085854375ee34f";

    private static byte[] generateSecretKey() throws NoSuchAlgorithmException {
//        KeyGenerator keygen = KeyGenerator.getInstance(MCRYPT_TRIPLEDES);
//        return keygen.generateKey().getEncoded();
        return StrConvertUtil.hexStrToByteArr(SecretKey);
    }

    private static String iv = "1f135852755a770b";

    private static byte[] randomIVBytes() {
//        Random ran = new Random();
//        byte[] bytes = new byte[8];
//        for (int i = 0; i < bytes.length; ++i) {
//            bytes[i] = (byte) ran.nextInt(Byte.MAX_VALUE + 1);
//        }
//        return bytes;
        return StrConvertUtil.hexStrToByteArr(iv);
    }

    public static void main(String args[]) throws Exception {
        String s = "833ad57dba40f75a";
        String s3 = TripleDESUtil.encrypt(s);
        
        String s4 = TripleDESUtil.decrypt(s3);
        System.out.println(s3);
        System.out.println(s4);
        
//        final byte[] secretBytes = TripleDESUtil.generateSecretKey();
//        System.out.println(StrConvertUtil.byteArrToHexStr(secretBytes));
//        System.out.println("==========" + secretBytes.length);
//        final byte[] ivbytes = TripleDESUtil.randomIVBytes();
//        System.out.println(StrConvertUtil.byteArrToHexStr(ivbytes));
//        System.out.println("==========" + ivbytes.length);
//        System.out.println("plain text: " + plainText);
//        byte[] encrypt = TripleDESUtil.encrypt(plainText.getBytes(), secretBytes, ivbytes);
//        System.out.println("cipher text: " + StrConvertUtil.byteArrToHexStr(encrypt));
//        byte[] encrypt = StrConvertUtil.hexStrToByteArr("40c984f77317562a4bf6a058d43f885aa8c3ab532c94c9ba");
//        System.out.println("decrypt text: " + new String(TripleDESUtil.decrypt(encrypt, secretBytes, ivbytes), "UTF-8"));
    }

}
