package com.leo.wheel.google;

import java.math.BigInteger;
import java.math.RoundingMode;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Range;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import com.google.common.primitives.Ints;

/**
 * API：http://tool.oschina.net/apidocs/apidoc?api=guava
 * 	测试Guava的常用方法
 * Preconditions：先决条件，主要用于检查参数；
 * Objects;
 * ComparisonChain;
 * 
 * 
 * @author leo
 *
 */
public class TestGuava {

	/**
	 * 
	 */
	@Test
	public void testPreconditions() {
		Object result = null;
		result = Preconditions.checkNotNull("fadsf");
		System.out.println(result);
	}

	/**
	 * equals;
	 * hashCode
	 * toString;
	 * compoare;
	 * 
	 */
	@Test
	public void testObjects() {
		Object result = null;
		// 使用Objects.equal帮助你执行null敏感的equals判断，从而避免抛出NullPointerException。
		System.out.println(Objects.equal("a", "a")); // returns true
		System.out.println(Objects.equal(null, "a")); // returns false
		System.out.println(Objects.equal("a", null)); // returns false
		System.out.println(Objects.equal(null, null)); // returns true

		// hashCode
		System.out.println(Objects.hashCode("fasdf"));

		// toString
		// Returns "ClassName{x=1}"
		result = MoreObjects.toStringHelper(this).add("x", 1).toString();
		System.out.println(result);
		// Returns "MyObject{x=1}"
		result = MoreObjects.toStringHelper("MyObject").add("x", 1).toString();
		System.out.println(result);

		// compare，相同为0，不相同为-1
		result = ComparisonChain.start().compare("aaa", "bbb").result();
		System.out.println(String.format("compare的结果为%s", result));

	}

	/**
	 * 
	 */
	@Test
	public void testStream() {
		// TODO
	}

	/**
	 * Guava区间，参考：http://ifeve.com/google-guava-ranges/
	 * 
	 */
	@Test
	public void testRange() {
		boolean isContains = false;
		isContains = Range.closed(1, 3).contains(2);// return true
		System.out.println(isContains);
		isContains = Range.closed(1, 3).contains(4);// return false
		System.out.println(isContains);
		isContains = Range.lessThan(5).contains(5); // return false
		System.out.println(isContains);
		isContains = Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3)); // return true
		System.out.println(isContains);
	}

	/**
	 * Java内建的散列码[hash code]概念被限制为32位，并且没有分离散列算法和它们所作用的数据，因此很难用备选算法进行替换。此外，使用Java内建方法实现的散列码通常是劣质的，部分是因为它们最终都依赖于JDK类中已有的劣质散列码。
	 * Object.hashCode往往很快，但是在预防碰撞上却很弱，也没有对分散性的预期。这使得它们很适合在散列表中运用，因为额外碰撞只会带来轻微的性能损失，
	 * 	同时差劲的分散性也可以容易地通过再散列来纠正（Java中所有合理的散列表都用了再散列方法）。
	 * 	然而，在简单散列表以外的散列运用中，Object.hashCode几乎总是达不到要求——因此，有了com.google.common.hash包。
	 * 	参考：http://ifeve.com/google-guava-hashing/
	 */
	@Test
	public void testHash() {
		long start = System.currentTimeMillis();
		HashFunction hf = Hashing.goodFastHash(32);
		HashCode hc = hf.newHasher().putLong(12323L).putString("test111", Charsets.UTF_8).hash();
		long end = System.currentTimeMillis();
		System.out.println(String.format("guava hash花费的时间为%sms", end - start));
		System.out.println(hc.toString());
	}

	/**
	 * 	参考：http://ifeve.com/google-guava-math/
	 * 
	 * 	测试Guava中常用的数学运算：
	 * Guava Math针对各种不常见的溢出情况都有充分的测试；对溢出语义，Guava文档也有相应的说明；如果运算的溢出检查不能通过，将导致快速失败；
	 * Guava Math的性能经过了精心的设计和调优；虽然性能不可避免地依据具体硬件细节而有所差异，但Guava Math的速度通常可以与Apache Commons的MathUtils相比，在某些场景下甚至还有显著提升；
	 * 	Guava Math在设计上考虑了可读性和正确的编程习惯；IntMath.log2(x, CEILING) 所表达的含义，即使在快速阅读时也是清晰明确的。而32-Integer.numberOfLeadingZeros(x – 1)对于阅读者来说则不够清晰。
	 * 
	 * Guava Math主要处理三种整数类型：int、long和BigInteger。这三种类型的运算工具类分别叫做IntMath、LongMath和BigIntegerMath。
	 */
	@Test
	public void testMath() {
		long n = 144L;
		int logFloor = LongMath.log2(n, RoundingMode.FLOOR);
		System.out.println(logFloor);
		int x = 10;
		int y = 2;
		int mustNotOverflow = IntMath.checkedMultiply(x, y);
		System.out.println(mustNotOverflow);
		long quotient = LongMath.divide(x, 3, RoundingMode.UNNECESSARY); // fail fast on non-multiple of 3
		System.out.println(quotient);
		double d = 10 / 3;
		BigInteger nearestInteger = DoubleMath.roundToBigInteger(d, RoundingMode.HALF_EVEN);
		System.out.println(nearestInteger);
		// BigInteger sideLength = BigIntegerMath.sqrt(x, RoundingMode.CEILING);

	}

}
