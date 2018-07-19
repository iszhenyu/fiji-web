package tech.jianshuo.helper;

import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author zhen.yu
 * @since 2017/5/21
 */
public class PrincipalHelper {

	private static final String REG_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	private static final String REG_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	public static boolean isMobile(String mobileNumber) {
		return isNotEmpty(mobileNumber) && Pattern.compile(REG_MOBILE).matcher(mobileNumber).matches();
	}

	public static boolean isEmail(String email) {
		return isNotEmpty(email) && Pattern.compile(REG_EMAIL).matcher(email).matches();
	}

}
