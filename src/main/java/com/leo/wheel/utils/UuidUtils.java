package com.leo.wheel.utils;

import java.util.UUID;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的。
 * 按照开放软件基金会(OSF)制定的标准计算，用到了以太网卡地址、纳秒级时间、芯片ID码和许多可能的数字。
* UUID由以下几部分的组合：
 *（1）当前日期和时间，UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同。
 *（2）时钟序列
 *（3）全局唯一的IEEE机器识别号，如果有网卡，从网卡MAC地址获得，没有网卡以其他方式获得。
*这UUID的唯一缺陷在于生成的结果串会比较长。关于UUID这个标准使用最普遍的是微软的GUID(Globals Unique Identifiers)。在ColdFusion中可以用CreateUUID()函数很简单的生成UUID，
*其格式为：xxxxxxxx-xxxx- xxxx-xxxxxxxxxxxxxxxx(8-4-4-16)，其中每个 x 是 0-9 或 a-f 范围内的一个十六进制的数字。
*而标准的UUID格式为：xxxxxxxx-xxxx-xxxx-xxxxxx-xxxxxxxxxx (8-4-4-4-12)，可以从cflib 下载CreateGUID() UDF进行转换。
*使用UUID的好处在分布式的软件系统中（比如：DCE/RPC, COM+,CORBA）就能体现出来，它能保证每个节点所生成的标识都不会重复，并且随着WEB服务等整合技术的发展，UUID的优势将更加明显。
*根据使用的特定机制，UUID不仅需要保证是彼此不相同的，或者最少也是与公元3400年之前其他任何生成的通用惟一标识符有非常大的区别。
*通用惟一标识符还可以用来指向大多数的可能的物体。微软和其他一些软件公司都倾向使用全球惟一标识符（GUID），这也是通用惟一标识符的一种类型，可用来指向组建对象模块对象和其他的软件组件。
*第一个通用惟一标识符是在网罗计算机系统（NCS）中创建，并且随后成为开放软件基金会（OSF）的分布式计算环境（DCE）的组件。
 * 
 * 2，通过JUG生成uuid的工具类，文档参考 https://github.com/cowtowncoder/java-uuid-generator/wiki
 * 
 * @author leo
 *
 */
public class UuidUtils {

	/**
	 * 通过Java提供的UUID生成全局唯一ID，缺陷是结果比较长
	 * @return
	 */
	public static String getNativeUUid(boolean isContainSeparator) {
		long start = System.currentTimeMillis();
		UUID uuid = UUID.randomUUID();
		String result = uuid.toString();
		// 去掉分隔符-
		if (!isContainSeparator) {
			result = result.replace("-", "");
		}
		long end = System.currentTimeMillis();
		System.out.println(String.format("Java生成UUID花费的时间为%sms", end - start));
		return result;
	}

	/**
	 * 通过Jug工具生成全局唯一ID，缺陷是结果串比较长
	 * @param isContainSeparator 是否包含分隔符
	 * @return
	 */
	public static String getUuidByJug(boolean isContainSeparator) {
		long start = System.currentTimeMillis();
		TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
		// RandomBasedGenerator randomBasedGenerator =
		// Generators.randomBasedGenerator();
		UUID uuid = gen.generate();
		String result = uuid.toString();
		// 去掉分隔符-
		if (!isContainSeparator) {
			result = result.replace("-", "");
		}
		long end = System.currentTimeMillis();
		System.out.println(String.format("JUG生成UUID花费的时间为%sms", end - start));
		return result;
	}

	public static void main(String[] args) {
		System.out.println(UuidUtils.getNativeUUid(false));
		System.out.println(UuidUtils.getUuidByJug(true));
	}
}
