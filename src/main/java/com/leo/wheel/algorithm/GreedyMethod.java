package com.leo.wheel.algorithm;

/**
 * 贪心算法，参考:https://www.cnblogs.com/xiaozhang2014/p/7783795.html
 * 所谓贪心算法是指，在对问题求解时，总是做出在当前看来最好的选择。也就是说，不从整体最优解出发来考虑，它所做出的仅是在某种意义上的局部最优解。 
 * 贪心算法不是对所有问题都能得到整体最优解，但对范围相当广泛的许多问题都能产生整体最优解或整体最优解的近似解。 
 * 贪心算法的基本思路如下： 
 * 1.建立数学模型来描述问题。 
 * 2.把求解的问题分成若干个子问题。 
 * 3.对每个子问题求解，得到每个子问题的局部最优解。 
 * 4.把每个子问题的局部最优解合成为原来问题的一个解。 
 * 实现该算法的过程： 
 * 从问题的某一初始状态出发； 
 * while 能朝给定总目标前进一步 do 
 * 求出可行解的一个解元素； 
 * 由所有解元素组合成问题的一个可行解； 
 * 
 * @author leo
 *
 */
public class GreedyMethod {
	public static void main(String[] args) {
		for (int i = 10; i < 200; i += 18) {
			greedyGiveMoney(i);
		}
	}

	public static void greedyGiveMoney(int money) {
		System.out.println("需要找零: " + money);
		int[] moneyLevel = { 1, 5, 10, 20, 50, 100 };
		for (int i = moneyLevel.length - 1; i >= 0; i--) {
			int num = money / moneyLevel[i];
			int mod = money % moneyLevel[i];
			money = mod;
			if (num > 0) {
				System.out.println("需要" + num + "张" + moneyLevel[i] + "块的");
			}
		}
	}
}
