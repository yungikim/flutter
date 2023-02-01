package com.lgcns.ucap.core.util.crypto;

import java.security.Key;

import javax.crypto.spec.IvParameterSpec;

public class CipherKeyDTD {

    public CipherKeyDTD() {
        cipherKey = null;
        ivParamSpec = null;
    }

    public Key getCipherKey() {
        return cipherKey;
    }

    public void setCipherKey(Key cipherKey) {
        this.cipherKey = cipherKey;
    }

    public IvParameterSpec getIvParamSpec() {
        return ivParamSpec;
    }

    public void setIvParamSpec(IvParameterSpec ivParamSpec) {
        this.ivParamSpec = ivParamSpec;
    }

    private Key cipherKey;
    private IvParameterSpec ivParamSpec;
}
