import org.junit.Assert;
import org.junit.Test;

import tech.jianshuo.fiji.common.constant.CommonConstants;
import tech.jianshuo.fiji.common.util.EncodeUtils;

/**
 * @author zhen.yu
 * Created on 2018-09-01
 */
public class EncodeUtilsTest {

    @Test
    public void testBase64() {
        final String testStr = "abc";
        Assert.assertEquals(testStr, EncodeUtils.base64Decode(EncodeUtils.base64Encode(testStr)));
    }

    @Test
    public void testHex() throws Exception {
        final String testStr = "abc";
        String encoded = EncodeUtils.hexEncode(testStr.getBytes(CommonConstants.DEFAULT_CHAR_SET));
        System.out.printf(encoded);
        Assert.assertEquals(testStr, new String(EncodeUtils.hexDecode(encoded), CommonConstants.DEFAULT_CHAR_SET));
    }
}
