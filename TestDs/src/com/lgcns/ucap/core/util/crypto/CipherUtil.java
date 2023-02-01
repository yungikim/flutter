package com.lgcns.ucap.core.util.crypto;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class CipherUtil {

    public CipherUtil() {
    }

    public static Key generateKey(String algorithm)
            throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        javax.crypto.SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static Key generateKey(String algorithm, byte keyData[]) {
        return new SecretKeySpec(keyData, algorithm);
    }

    public static byte[] md5(String pvSrcStr)
            throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return messageDigest.digest(pvSrcStr.getBytes());
    }

    public static byte[] sha1(String pvSrcStr)
            throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        return messageDigest.digest(pvSrcStr.getBytes());
    }

    public static String sha1String(String pvSrcStr)
            throws NoSuchAlgorithmException {
        byte byteSha1[] = sha1(pvSrcStr);
        return ByteUtils.toHexString(byteSha1);
    }

    public static String md5String(String pvSrcStr)
            throws NoSuchAlgorithmException {
        byte byteMD5[] = md5(pvSrcStr);
        return new String(byteMD5);
    }

    
    public static byte[] getSha1(String src) {

    	byte [] br = null;
    	try {
    		br = MessageDigest.getInstance("SHA1").digest(src.getBytes());
    	} catch (Exception e) {
    		e.printStackTrace();
        }
    	return br;
    }
    public static CipherKeyDTD makeCipherKey(String pvAlgorithm, String pvUserKey)
            throws Exception {
        byte byteKey[] = new byte[16];
        byte byteIV[] = new byte[16];
        CipherKeyDTD cipherKeyDTD = new CipherKeyDTD();
        byte hashKey[] = CipherUtil.getSha1(pvUserKey);
        byte imsiIV[] = new byte[4];
        for (int i = 0; i < hashKey.length; i++)
            if (i < 16)
                byteKey[i] = hashKey[i];
            else
                imsiIV[i - 16] = hashKey[i];

        String hexIV = ByteUtils.toHexString(imsiIV);
        byte shaIV[] = sha1(hexIV);
        for (int i = 0; i < 16; i++)
            byteIV[i] = shaIV[i];
        cipherKeyDTD.setCipherKey(generateKey("AES", byteKey));
        cipherKeyDTD.setIvParamSpec(new IvParameterSpec(byteIV));
        return cipherKeyDTD;
    }
}
