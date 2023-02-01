
package com.lgcns.ucap.core.util.crypto;

import com.lgcns.ucap.core.util.LogUtil;
import com.lgcns.ucap.core.util.StringUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class AES256 extends AbstractNvCrypto {
    // http://3030.tistory.com/134
    // http://blog.kangwoo.kr/44
    // http://www.androidpub.com/818763

    private static final String LOG_TAG = AES256.class.getSimpleName();

    private Cipher encrytCipher = null;

    private Cipher decrytCipher = null;

    private String password = null;

    private SecretKeySpec spec = null;

    protected AES256Handler nvCryptoHandler = null;

    public static AES256 newInstance(String password) {
        return newInstance(defaultAES256Handler, password);
    }

    public static AES256 newInstance(AES256Handler handler, String password) {
        try {
            return new AES256(handler, password);
        } catch (Exception e) {
            LogUtil.e(LOG_TAG, e);
            return null;
        }
    }

    private AES256(AES256Handler handler, String password) throws Exception {
        this.nvCryptoHandler = handler;

        this.encrytCipher = Cipher.getInstance(this.nvCryptoHandler.getTransformation(),
                this.nvCryptoHandler.getProvider());
        this.decrytCipher = Cipher.getInstance(this.nvCryptoHandler.getTransformation(),
                this.nvCryptoHandler.getProvider());

        this.password = password;
        this.spec = getKeySpec();

        this.encrytCipher.init(Cipher.ENCRYPT_MODE, this.spec,
                this.nvCryptoHandler.getInitialVecter());
        this.decrytCipher.init(Cipher.DECRYPT_MODE, this.spec,
                this.nvCryptoHandler.getInitialVecter());
    }

    private SecretKeySpec getKeySpec() {
        try {
            byte[] keyStrBytes = this.password.getBytes();
            byte[] keyBytes = new byte[32];
            for (int i = 0; i < keyBytes.length; i++) {
                if (i < keyStrBytes.length)
                    keyBytes[i] = keyStrBytes[i];
                else
                    keyBytes[i] = 0x00;
            }

            return new SecretKeySpec(keyBytes, this.nvCryptoHandler.getAlgorithm());
        } catch (Exception e) {
            LogUtil.e(LOG_TAG, e);
            return null;
        }
    }

    @Override
    public String encrypt(String text) {
        try {
            if (StringUtil.isEmpty(text))
                return null;
            
            // return Base64.encodeToString(encrytCipher.doFinal(text.getBytes()), Base64.DEFAULT);
            return Base64.getEncoder().encodeToString(encrytCipher.doFinal(text.getBytes()));
        } catch (Exception e) {
            LogUtil.e(LOG_TAG, e);
            return null;
        }
    }

    @Override
    public byte[] encrypt(byte[] text) {
        try {
            if (text != null && text.length == 0)
                return null;
            return encrytCipher.doFinal(text);
        } catch (Exception e) {
            LogUtil.e(LOG_TAG, e);
            return null;
        }
    }

    @Override
    public String decrypt(String text) {
        try {
            if (StringUtil.isEmpty(text))
                return null;
            // return new String(decrytCipher.doFinal(Base64.decode(text, Base64.DEFAULT)));
            return new String(decrytCipher.doFinal(Base64.getDecoder().decode(text)));
        } catch (Exception e) {
            LogUtil.e(LOG_TAG, e);
            return null;
        }
    }

    @Override
    public byte[] decrypt(byte[] text) {
        try {
            if (text != null && text.length == 0)
                return null;
            return decrytCipher.doFinal(text);
        } catch (Exception e) {
            LogUtil.e(LOG_TAG, e);
            return null;
        }
    }

    public interface AES256Handler extends INvCryptoHandler {
        public IvParameterSpec getInitialVecter();

        public String getTransformation();

        public String getProvider();

        public String getAlgorithm();
    }

    private static AES256Handler defaultAES256Handler = new AES256Handler() {
        @Override
        public String getTransformation() {
            return "AES/CBC/PKCS7Padding";
        }

        @Override
        public String getProvider() {
            return "BC";
        }

        @Override
        public IvParameterSpec getInitialVecter() {
            return new IvParameterSpec(new byte[] {
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            });
        }

        @Override
        public String getAlgorithm() {
            return "AES";
        }
    };
}
