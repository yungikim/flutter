
package com.lgcns.ucap.core.util.crypto;

public abstract class AbstractNvCrypto {

    protected INvCryptoHandler nvCryptoHandler = null;

    abstract public String encrypt(String text);

    abstract public byte[] encrypt(byte[] text);

    abstract public String decrypt(String text);

    abstract public byte[] decrypt(byte[] text);

    public void setCryptoHandler(INvCryptoHandler nvCryptoHandler) {
        this.nvCryptoHandler = nvCryptoHandler;
    }
}
