package tech.jianshuo.fiji.common.util;

import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * @author zhen.yu
 * @since 2017/6/8
 */
public class CipherUtils {

    public static String hmacSHA256Digest(String key, String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] keyBytes = key.getBytes("utf-8");
            byte[] contentBytes = content.getBytes("utf-8");

            SecretKey secret = new SecretKeySpec(keyBytes, "HMACSHA256");
            mac.init(secret);

            byte[] doFinal = mac.doFinal(contentBytes);
            byte[] hexB = new Hex().encode(doFinal);
            return new String(hexB, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String hmacSHA256Digest(String key, Map<String, String> map) {
        StringBuilder s = new StringBuilder();
        map.values().forEach(s::append);
        return hmacSHA256Digest(key, s.toString());
    }

}
