package com.leo.wheel.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 	正则表达式，语法参考
 * https://github.com/YUSHEN2015/RegexUtils/blob/master/java%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%E8%AF%AD%E6%B3%95%E5%A4%A7%E5%85%A8
 * @author leo
 *
 */
public class RegexUtils {

	private static final String IMG_REGEX = "\\w+\\.(jpg|gif|bmp|png).*";// 图片的正则表达式
	private static final String EMAIL_REGEX = "\\\\w+@\\\\w+\\\\.[a-z]+(\\\\.[a-z]+)?";// EMAIL的正则表达式
	private static final String ID_CARD_REGEX = "[1-9]\\\\d{16}[a-zA-Z0-9]{1}";// 身份证正则
	private static final String MOBILE_REGEX = "(\\\\+\\\\d+)?1[3458]\\\\d{9}$";// 手机正则
	private static final String PHONE_REGEX = "(\\\\+\\\\d+)?(\\\\d{3,4}\\\\-?)?\\\\d{7,8}$";// 固定电话正则
	private static final String DIGIT_REGEX = "\\\\-?[1-9]\\\\d+";// 整数正则
	private static final String DECIMALS_REGEX = "\\\\-?[1-9]\\\\d+(\\\\.\\\\d+)?";// 浮点数正则
	private static final String CHINESE_REGEX = "^[\\u4E00-\\u9FA5]+$";// 中文正则
	private static final String DATE_REGEX = "[1-9]{4}([-./])\\\\d{1,2}\\\\1\\\\d{1,2}";// 日期正则
	private static final String URL_REGEX = "(https?://(w{3}\\\\.)?)?\\\\w+\\\\.\\\\w+(\\\\.[a-zA-Z]+)*(:\\\\d{1,5})?(/\\\\w*)*(\\\\??(.+=.*)?(&.+=.*)?)?";// URL正则
	private static final String IP_REGEX = "[1-9](\\\\d{1,2})?\\\\.(0|([1-9](\\\\d{1,2})?))\\\\.(0|([1-9](\\\\d{1,2})?))\\\\.(0|([1-9](\\\\d{1,2})?))";// IP正则
	private static final String LINUX_REGEX = "Linux.*";// linux系统
	private static final String WIN_REGEX = "Windows.*";// windows系统
	private static final String MAC_REGEX = "Mac.*";// mac系统

	/**
	 * 验证文件是否是图片
	 * @param fileName
	 * @return
	 */
	public static boolean checkImg(String fileName) {
		// 图片的正则表达式
		if(StringUtils.isBlank(fileName)) {
			return false;
		}
		return Pattern.matches(IMG_REGEX, fileName.replace("/", "").replace(":", ""));
	}

	/**
	 * 	校验是否是Linux系统
	 * @param osName
	 * @return
	 */
	public static boolean isLinuxOS(String osName) {
		return Pattern.matches(LINUX_REGEX, osName);
	}

	/**
	 *	校验是否是Window系统
	 * @param osName
	 * @return
	 */
	public static boolean isWindowOS(String osName) {
		return Pattern.matches(WIN_REGEX, osName);
	}

	/**
	 * 	检验是否是Mac系统
	 * @param osName
	 * @return
	 */
	public static boolean isMacOS(String osName) {
		return Pattern.matches(MAC_REGEX, osName);
	}

	/**
	* 验证Email
	* @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
	* @return 验证成功返回true，验证失败返回false
	*/
	public static boolean checkEmail(String email) {
		return Pattern.matches(EMAIL_REGEX, email);
	}

	/**
	 * 验证身份证号码
	 * @param idCard 居民身份证号码18位，第一位不能为0，最后一位可能是数字或字母，中间16位为数字 \d同[0-9]
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkIdCard(String idCard) {
		return Pattern.matches(ID_CARD_REGEX, idCard);
	}

	/**
	 * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 * @param mobile 移动、联通、电信运营商的号码段
	 *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
	 *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
	 *<p>电信的号段：133、153、180（未启用）、189</p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkMobile(String mobile) {
		return Pattern.matches(MOBILE_REGEX, mobile);
	}

	/**
	* 验证固定电话号码
	* @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
	* <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
	*  数字之后是空格分隔的国家（地区）代码。</p>
	* <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
	* 对不使用地区或城市代码的国家（地区），则省略该组件。</p>
	* <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
	* @return 验证成功返回true，验证失败返回false
	*/
	public static boolean checkPhone(String phone) {
		return Pattern.matches(PHONE_REGEX, phone);
	}

	/**
	 * 验证整数（正整数和负整数）
	 * @param digit 一位或多位0-9之间的整数
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDigit(String digit) {
		return Pattern.matches(DIGIT_REGEX, digit);
	}

	/**
	 * 验证整数和浮点数（正负整数和正负浮点数）
	 * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDecimals(String decimals) {
		return Pattern.matches(DECIMALS_REGEX, decimals);
	}

	/**
	 * 验证中文
	 * @param chinese 中文字符
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkChinese(String chinese) {
		return Pattern.matches(CHINESE_REGEX, chinese);
	}

	/**
	 * 验证日期（年月日）
	 * @param birthday 日期，格式：1992-09-03，或1992.09.03
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkBirthday(String birthday) {
		return Pattern.matches(DATE_REGEX, birthday);
	}

	/**
	 * 验证URL地址
	 * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkURL(String url) {
		return Pattern.matches(URL_REGEX, url);
	}

	/**
	 * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
	 * @param ipAddress IPv4标准地址
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkIpAddress(String ipAddress) {
		return Pattern.matches(IP_REGEX, ipAddress);
	}
}
