package tech.jianshuo.component.util;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * @author zhen.yu
 * @since 2017/6/8
 */
public class DigestUtils {
    private static final Logger logger = LoggerFactory.getLogger(DigestUtils.class);

    public static String md5Digest(String oriText) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(oriText.getBytes("UTF8"));
            byte s[] = digest.digest();
            StringBuilder builder = new StringBuilder();
            for (byte b: s) {
                builder.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
            return builder.toString();
        } catch (Exception e) {
            logger.error("md5 digest error, oriText: {}", oriText, e);
        }

        return null;
    }

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

}
