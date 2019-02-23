package com.leo.wheel.exceptions;

import org.junit.Test;

/**
 * JVM常见的一场，以及解决方案：https://www.cnblogs.com/duanxz/p/4901437.html
 * 
 * @author leo
 *
 */
public class Exceptions {

	/**
	 * 数组大于1000000000时，出现java.lang.OutOfMemoryError: Java heap space；
	 * 原因：Heap内存溢出，意味着Young和Old generation的内存不够。
	 * 解决：调整java启动参数 -Xms -Xmx 来增加Heap内存。
	 * 
	 * 堆内存溢出时，首先判断当前最大内存是多少（参数：-Xmx 或 -XX:MaxHeapSize=），可以通过命令 jinfo -flag MaxHeapSize 查看运行中的JVM的配置，
	 * 如果该值已经较大则应通过 mat 之类的工具查找问题，
	 * 或 jmap -histo查找哪个或哪些类占用了比较多的内存。
	 * 参数-verbose:gc(-XX:+PrintGC) -XX:+PrintGCDetails可以打印GC相关的一些数据。
	 * 如果问题比较难排查也可以通过参数-XX:+HeapDumpOnOutOfMemoryError在OOM之前Dump内存数据再进行分析。
	 * 此问题也可以通过histodiff打印多次内存histogram之前的差值，有助于查看哪些类过多被实例化，如果过多被实例化的类被定位到后可以通过btrace再跟踪。
	 * 
	 */
	@Test
	public void testOutOfMemoryErrorByHeapSpace() {
		int[] source = new int[1000000000];
		for (int i = 0; i < source.length; i++) {
			source[i] = i;
		}
	}

	/**
	 * java.lang.OutOfMemoryError: unable to create new native thread
	 * 原因：Stack空间不足以创建额外的线程，要么是创建的线程过多，要么是Stack空间确实小了。
	 * 解决：由于JVM没有提供参数设置总的stack空间大小，但可以设置单个线程栈的大小；而系统的用户空间一共是3G，
	 * 除了Text/Data/BSS/MemoryMapping几个段之外，Heap和Stack空间的总量有限，是此消彼长的。
	 * 因此遇到这个错误，可以通过两个途径解决：
	 * 1.通过-Xss启动参数减少单个线程栈大小，这样便能开更多线程（当然不能太小，太小会出现StackOverflowError）；
	 * 2.通过-Xms -Xmx 两参数减少Heap大小，将内存让给Stack（前提是保证Heap空间够用）。
	 * 
	 */
	@Test
	public void testOutOfMemoryErrorByThread() {
		while (true) {
			new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(60 * 60 * 1000);
					} catch (InterruptedException e) {
					}
				}
			}).start();
		}
	}

	/**
	 * java.lang.OutOfMemoryError: PermGen space
	 * 原因：Permanent Generation空间不足，不能加载额外的类；
	 * 解决：调整-XX:PermSize= -XX:MaxPermSize= 两个参数来增大PermGen内存。
	 * 一般情况下，这两个参数不要手动设置，只要设置-Xmx足够大即可，JVM会自行选择合适的PermGen大小。
	 */
	@Test
	public void testOutOfMemoryErrorByPermGen() {

	}

	/**
	 * java.lang.OutOfMemoryError: Requested array size exceeds VM limit
	 */
	@Test
	public void testOutOfMemoryErrorByArray() {
		int[] source = new int[1000000000];
		for (int i = 0; i < source.length; i++) {
			source[i] = i;
		}
	}
}
