package tech.jianshuo.component.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * @author zhenyu
 * Created on 2018-10-21
 */
public class CharUtils {

    public static Manipulation none() {
        return Manipulation.NONE;
    }

    public static Manipulation separatedToCamel() {
        return Manipulation.SEPARATED_TO_CAMELCASE;
    }

    public static Manipulation camelToHyphen() {
        return Manipulation.CAMELCASE_TO_HYPHEN;
    }

    public enum Manipulation {

        /**
         * none
         */
        NONE {
            @Override
            public String apply(String value) {
                return value;
            }
        },

        /**
         * 横线转下划线
         */
        HYPHEN_TO_UNDERSCORE {
            @Override
            public String apply(String value) {
                return value.indexOf('-') != -1 ? value.replace('-', '_') : value;
            }
        },

        /**
         * 下划线转点
         */
        UNDERSCORE_TO_PERIOD {
            @Override
            public String apply(String value) {
                return value.indexOf('_') != -1 ? value.replace('_', '.') : value;
            }
        },

        /**
         * 点转下划线
         */
        PERIOD_TO_UNDERSCORE {
            @Override
            public String apply(String value) {
                return value.indexOf('.') != -1 ? value.replace('.', '_') : value;
            }
        },

        /**
         * 驼峰转下划线
         */
        CAMELCASE_TO_UNDERSCORE {
            @Override
            public String apply(String value) {
                if (value.isEmpty()) {
                    return value;
                }
                Matcher matcher = CAMEL_CASE_PATTERN.matcher(value);
                if (!matcher.find()) {
                    return value;
                }
                matcher = matcher.reset();
                StringBuffer result = new StringBuffer();
                while (matcher.find()) {
                    matcher.appendReplacement(result, matcher.group(1) + '_' + StringUtils.uncapitalize(matcher.group(2)));
                }
                matcher.appendTail(result);
                return result.toString();
            }
        },

        /**
         * 驼峰转下横线
         */
        CAMELCASE_TO_HYPHEN {
            @Override
            public String apply(String value) {
                if (value.isEmpty()) {
                    return value;
                }
                Matcher matcher = CAMEL_CASE_PATTERN.matcher(value);
                if (!matcher.find()) {
                    return value;
                }
                matcher = matcher.reset();
                StringBuffer result = new StringBuffer();
                while (matcher.find()) {
                    matcher.appendReplacement(result, matcher.group(1) + '-'
                            + StringUtils.uncapitalize(matcher.group(2)));
                }
                matcher.appendTail(result);
                return result.toString();
            }
        },

        SEPARATED_TO_CAMELCASE {
            @Override
            public String apply(String value) {
                return separatedToCamelCase(value, false);
            }
        },

        CASE_INSENSITIVE_SEPARATED_TO_CAMELCASE {
            @Override
            public String apply(String value) {
                return separatedToCamelCase(value, true);
            }
        };

        private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("([^A-Z-])([A-Z])");
        private static final Pattern SEPARATED_TO_CAMEL_CASE_PATTERN = Pattern.compile("[_\\-.]");
        private static final char[] SUFFIXES = new char[]{'_', '-', '.'};

        public abstract String apply(String value);

        private static String separatedToCamelCase(String value, boolean caseInsensitive) {
            if (value.isEmpty()) {
                return value;
            }
            StringBuilder builder = new StringBuilder();
            for (String field : SEPARATED_TO_CAMEL_CASE_PATTERN.split(value)) {
                field = (caseInsensitive ? field.toLowerCase() : field);
                builder.append(builder.length() == 0 ? field : StringUtils.capitalize(field));
            }
            char lastChar = value.charAt(value.length() - 1);
            for (char suffix : SUFFIXES) {
                if (lastChar == suffix) {
                    builder.append(suffix);
                    break;
                }
            }
            return builder.toString();
        }

    }
}
