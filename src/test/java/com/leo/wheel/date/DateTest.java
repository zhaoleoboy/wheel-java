package com.leo.wheel.date;

import java.util.Date;

import org.junit.Test;

import com.leo.wheel.utils.DateUtils;

public class DateTest {

	@Test
	public void printResult() {
		System.out.println(DateUtils.getCurrentDate());
		System.out.println(DateUtils.getCurrentLocalDateTime());
		System.out.println(DateUtils.getCurDateTime(DateUtils.DATE_FORMAT_FULL));

		System.out.println(DateUtils.getSubDays("2019-01-22", "2018-12-21"));
		System.out.println(DateUtils.parseDateToString(new Date(), DateUtils.DATE_FORMAT_FULL));
	}

}
