package com.lgcns.ucap.core.util.crypto;


import com.lgcns.ucap.core.util.LogUtil;

import javax.crypto.Cipher;

public class AesCipher {
	
	static String AES_ENC_KEY = "DaesangSSOProject";

    public AesCipher() {
    }

    public static String encrypt(String pvSource) {
        String result = null;
        try {
            CipherKeyDTD cipherKeyDTD = new CipherKeyDTD();
            cipherKeyDTD = CipherUtil.makeCipherKey("AES", AES_ENC_KEY);
            String transformation = "AES/CBC/PKCS5Padding";
            Cipher cipher = Cipher.getInstance(transformation);

            cipher.init(1, cipherKeyDTD.getCipherKey(), cipherKeyDTD.getIvParamSpec());

            byte plain[] = pvSource.getBytes();
            byte encrypt[] = cipher.doFinal(plain);
            result = ByteUtils.toHexString(encrypt);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "[ERROR]";
        }
        LogUtil.d("AesCipher", " >> encrypt result = {} ", result);

        return result;
    }

    public static String decrypt(String pvUserKey, String pvSource) {
        String result;
        try {
            CipherKeyDTD cipherKeyDTD = new CipherKeyDTD();
            cipherKeyDTD = CipherUtil.makeCipherKey("AES", pvUserKey);
            String transformation = "AES/CBC/PKCS5Padding";
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(2, cipherKeyDTD.getCipherKey(), cipherKeyDTD.getIvParamSpec());
            byte byteSource[] = ByteUtils.toBytesFromHexString(pvSource);
            byte decrypt[] = cipher.doFinal(byteSource);
            result = new String(decrypt);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "[ERROR]";
        }
        return result;
    }
    
    public static void main(String [] arg1) {
    	System.out.println(encrypt("pulpal5479!!"));
    }
}
