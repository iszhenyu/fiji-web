package tech.jianshuo.fiji.common.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 */
public class EncryptUtils {

    public static final String CHAR_ENCODING = "UTF-8";

    private static byte[] encode(byte[] key, byte[] data) throws Exception {
        final String algorithm = "DESede";
        SecretKey secretKey = new SecretKeySpec(key, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private static byte[] decode(byte[] key, byte[] value) throws Exception {
        final String algorithm = "DESede";
        SecretKey secretKey = new SecretKeySpec(key, algorithm);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(value);
    }

    public static String encode(String key, String data) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] dataByte = data.getBytes(CHAR_ENCODING);
            byte[] valueByte = encode(keyByte, dataByte);
            return new String(EncodeUtils.base64Encode(valueByte), CHAR_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decode(String key, String value) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] valueByte = EncodeUtils.base64Decode(value.getBytes(CHAR_ENCODING));
            byte[] dataByte = decode(keyByte, valueByte);
            return new String(dataByte, CHAR_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptToHex(String key, String data) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] dataByte = data.getBytes(CHAR_ENCODING);
            byte[] valueByte = encode(keyByte, dataByte);
            return EncodeUtils.hexEncode(valueByte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptFromHex(String key, String value) {
        try {
            byte[] keyByte = key.getBytes(CHAR_ENCODING);
            byte[] valueByte = EncodeUtils.hexDecode(value);
            byte[] dataByte = decode(keyByte, valueByte);
            return new String(dataByte, CHAR_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String udpEncrypt(String key, String data) {
        try {
            Key k = updGenerateKey(key);
            IvParameterSpec IVSpec = new IvParameterSpec(new byte[8]);
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(1, k, ((IVSpec)));
            byte output[] = c.doFinal(data.getBytes("UTF-8"));
            return new String(EncodeUtils.base64Decode(output), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Key updGenerateKey(String key) {
        try {
            DESedeKeySpec KeySpec = new DESedeKeySpec(UdpHexDecode(key));
            SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance("DESede");
            Key k = ((KeyFactory.generateSecret(((KeySpec)))));
            return k;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String udpDecrypt(String key, String data) {
        try {
            byte[] input = EncodeUtils.base64Decode(data.getBytes("UTF-8"));
            Key k = updGenerateKey(key);
            IvParameterSpec IVSpec = new IvParameterSpec(new byte[8]);
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(2, k, ((IVSpec)));
            byte output[] = c.doFinal(input);
            return new String(output, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] UdpHexDecode(String s) {
        byte abyte0[] = new byte[s.length() / 2];
        String s1 = s.toLowerCase();
        for (int i = 0; i < s1.length(); i += 2) {
            char c = s1.charAt(i);
            char c1 = s1.charAt(i + 1);
            int j = i / 2;
            if (c < 'a') abyte0[j] = (byte) (c - 48 << 4);
            else abyte0[j] = (byte) ((c - 97) + 10 << 4);
            if (c1 < 'a') abyte0[j] += (byte) (c1 - 48);
            else abyte0[j] += (byte) ((c1 - 97) + 10);
        }
        return abyte0;
    }

}