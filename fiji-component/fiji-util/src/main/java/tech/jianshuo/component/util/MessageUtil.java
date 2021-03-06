package tech.jianshuo.component.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  MessageUtil
 */
public class MessageUtil {

    public static String getMessage(String template, String... keys) {
        int count = 0;
        for (String key : keys) {
            template = template.replace("{" + count++ + "}", key);
        }
        return template;
    }

    public static List<String> getAtUser(String str) {
        Pattern p = Pattern.compile("(?<=@).*?(?= )");
        Matcher m = p.matcher(str);
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            if (StringUtils.isNoneBlank(m.group().trim())) {
                result.add(m.group().trim());
            }
        }
        return result;
    }

}
