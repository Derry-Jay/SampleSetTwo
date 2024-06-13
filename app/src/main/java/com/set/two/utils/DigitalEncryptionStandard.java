package com.set.two.utils;

import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class DigitalEncryptionStandard {
    private static final String UNICODE_FORMAT = "UTF8";
    byte[] keyAsBytes;
    SecretKey key;
    private final Cipher cipher;

    public DigitalEncryptionStandard() throws Exception {
// TODO code application logic here
        String myEncryptionKey = "ThisIsSecretEncryptionKey", myEncryptionScheme = "DESede";
        keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        KeySpec myKeySpec = new DESedeKeySpec(keyAsBytes);
        SecretKeyFactory mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = mySecretKeyFactory.generateSecret(myKeySpec);
    }

    private String bytes2String(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuffer.append((char) aByte);
        }
        return stringBuffer.toString();
    }

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            Encoder base64encoder = Base64.getEncoder();
            encryptedString = Arrays.toString(base64encoder.encode(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            Decoder base64decoder = Base64.getDecoder();
            byte[] encryptedText = base64decoder.decode(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = bytes2String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}