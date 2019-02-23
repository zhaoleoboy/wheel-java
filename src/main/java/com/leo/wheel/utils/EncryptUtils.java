package com.leo.wheel.utils;

import java.io.IOException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
   * 	关于加密的工具类，主要利用的commons-codec工具包
 * MD5：非对称加密算法，不可逆！一般多用于登录注册功能。
 * Base64：可逆！
 * SHA:安全哈希算法（Secure Hash Algorithm）主要适用于数字签名标准（Digital Signature Standard DSS）里面定义的数字签名算法（Digital Signature Algorithm DSA）。不可逆！
 * HMAC:通过秘钥解密
 * @author leo
 *
 */
public class EncryptUtils {

	/**
	 * Base64编码
	 * 	可用于在HTTP环境下传递较长的标识信息。例如，在Java Persistence系统Hibernate中，
	 *	 就采用了Base64来将一个较长的唯一标识符（一般为128-bit的UUID）编码为一个字符串，用作HTTP表单和HTTP GET URL中的参数。
	 * 	在其他应用程序中，也常常需要把二进制数据编码为适合放在URL（包括隐藏表单域）中的形式。
	 * 	此时，采用Base64编码不仅比较简短，同时也具有不可读性，即所编码的数据不会被人用肉眼所直接看到。
	 * 
	 * @param source
	 * @return
	 */
	public static String encodeByBase64(String source) {
		long start = System.currentTimeMillis();
		byte[] byte64 = Base64.encodeBase64(source.getBytes(), true);
		String result = new String(byte64);
		long end = System.currentTimeMillis();
		System.out.println(String.format("Base64加密花费的时间为%sms", end - start));
		return result;
	}

	public static String decodeByBase64(String dest) throws IOException {
		long start = System.currentTimeMillis();
		byte[] rev = Base64.decodeBase64(dest.getBytes());
		String result = new String(rev);
		long end = System.currentTimeMillis();
		System.out.println(String.format("Base64解密花费的时间为%sms", end - start));
		return result;
	}

	/**
	 * SHA家族的五个算法，分别是SHA-1、SHA-224、SHA-256、SHA-384，和SHA-512，由美国国家安全局（NSA）所设计，并由美国国家标准与技术研究院（NIST）发布；
	 * 
	 *	安全哈希算法（Secure Hash Algorithm）主要适用于数字签名标准（Digital Signature Standard DSS）里面定义的数字签名算法（Digital Signature Algorithm DSA）。
	 *	对于长度小于2^64位的消息，SHA1会产生一个160位的消息摘要。该算法经过加密专家多年来的发展和改进已日益完善，并被广泛使用。
	 *	该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），
	 *	并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。
	 *	散列函数值可以说是对明文的一种"指纹"或是"摘要"所以对散列值的数字签名就可以视为对此明文的数字签名。
	 *
	 * @param source
	 * @return
	 */
	public static String encodeBySha256(String source) {
		long start = System.currentTimeMillis();
		MessageDigest sha256Digest = DigestUtils.getSha256Digest();
		byte[] digest = sha256Digest.digest(source.getBytes());
		String result = new String(digest);
		long end = System.currentTimeMillis();
		System.out.println(String.format("Sha256加密花费的时间为%sms", end - start));
		return result;
	}

	public static String encodeBySha384(String source) {
		long start = System.currentTimeMillis();
		MessageDigest sha384Digest = DigestUtils.getSha384Digest();
		byte[] digest = sha384Digest.digest(source.getBytes());
		String result = new String(digest);
		long end = System.currentTimeMillis();
		System.out.println(String.format("Sha384加密花费的时间为%sms", end - start));
		return result;
	}

	public static String encodeBySha512(String source) {
		long start = System.currentTimeMillis();
		MessageDigest sha512Digest = DigestUtils.getSha512Digest();
		byte[] digest = sha512Digest.digest(source.getBytes());
		String result = new String(digest);
		long end = System.currentTimeMillis();
		System.out.println(String.format("Sha512加密花费的时间为%sms", end - start));
		return result;
	}

	/**
	 * HMAC(Hash Message Authentication Code，散列消息鉴别码，基于密钥的Hash算法的认证协议。消息鉴别码实现鉴别的原理是，
	 * 	用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
	 * 	使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等。
	 * TODO JAVA实现Hmac加密
	 * @param source
	 * @return
	 */
	public static String encodeByHmac(String source) {
		String result = "";
		return result;
	}

	/**
	 * MD5加密算法具有以下特点：
	 * 1、压缩性：任意长度的数据，算出的MD5值长度都是固定的，长度为16进制32位。
	 * 2、容易计算：从原数据计算出MD5值很容易。
	 * 3、抗修改性：对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别。
	 * 4、强抗碰撞：已知原数据和其MD5值，想找到一个具有相同MD5值的数据（即伪造数据）是非常困难的。
	 * MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）。
	 *	 除了MD5以外，其中比较有名的还有sha-1、RIPEMD以及Haval等。
	 *
	 * MD5 是非对称的加密算法（PS：对称加密就是加密用的密码和解密用的密码是一样的，非对称就是加密和解密用的密钥不一样）
	 * 
	 * @param source
	 * @return
	 */
	public static String encodeByMd5(String source, String key) {
		long start = System.currentTimeMillis();
		String result = DigestUtils.md5Hex(source + key);
		long end = System.currentTimeMillis();
		System.out.println(String.format("MD5加密花费的时间为%sms", end - start));
		return result;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(EncryptUtils.encodeByBase64("testttttttt"));
		System.out.println(EncryptUtils.decodeByBase64("dGVzdHR0dHR0dHQ="));
		System.out.println(EncryptUtils.encodeBySha256("testttttttt"));
		System.out.println(EncryptUtils.encodeBySha384("testttttttt"));
		System.out.println(EncryptUtils.encodeBySha512("testttttttt"));
		System.out.println(EncryptUtils.encodeByMd5("testssssssssss", "keymd55555555"));
	}
}
