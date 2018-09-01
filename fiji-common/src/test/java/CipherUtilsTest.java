import org.junit.Assert;
import org.junit.Test;

import tech.jianshuo.fiji.common.util.CipherUtils;

/**
 * @author zhen.yu
 * Created on 2018-09-01
 */
public class CipherUtilsTest {

    @Test
    public void testMd5() {
        String ori = "abc";
        Assert.assertEquals("900150983cd24fb0d6963f7d28e17f72", CipherUtils.md5Digest(ori));
    }

}
