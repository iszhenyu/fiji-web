package tech.jianshuo.fiji.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tech.jianshuo.fiji.common.constant.CommonConstants;

/**
 * @author zhen.yu
 * Created on 2018-09-01
 */
public class EncodeUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncodeUtils.class);

    public static String base64Encode(String rawData) {
        try {
            return Base64.getEncoder().encodeToString(rawData.getBytes(CommonConstants.DEFAULT_CHAR_SET));
        } catch (UnsupportedEncodingException e) {
            logger.error("not support encoding: {}", CommonConstants.DEFAULT_CHAR_SET, e);
        }
        return null;
    }

    public static byte[] base64Encode(byte[] rawData) {
        return Base64.getEncoder().encode(rawData);
    }

    public static String base64Decode(String encodedData) {
        try {
            byte[] bytes = Base64.getDecoder().decode(encodedData);
            return new String(bytes, CommonConstants.DEFAULT_CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            logger.error("not support encoding: {}", CommonConstants.DEFAULT_CHAR_SET, e);
        }
        return null;
    }

    public static byte[] base64Decode(byte[] encodedData) {
        return Base64.getDecoder().decode(encodedData);
    }

    public static String hexEncode(byte input[]) {
        if (input == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder(input.length * 2);
        for (byte b: input) {
            int current = b & 0xff;
            if (current < 16) {
                builder.append("0");
            }
            builder.append(Integer.toString(current, 16));
        }
        return builder.toString();
    }

    public static byte[] hexDecode(String input) {
        if (input == null) {
            return null;
        }
        byte output[] = new byte[input.length() / 2];
        for (int i = 0; i < output.length; i++)
            output[i] = (byte) Integer.parseInt(input.substring(i * 2, (i + 1) * 2), 16);

        return output;
    }
}
