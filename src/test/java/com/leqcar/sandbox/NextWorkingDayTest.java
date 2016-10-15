package com.leqcar.sandbox;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class NextWorkingDayTest {

	private TemporalAdjuster weekEndAdjuster;
	
	@Before
	public void before() {
		weekEndAdjuster = new NextWorkingDay();
	}
	
	@Test
	public void testWhenSaturdayReturnMonday() {
		
		LocalDate date = LocalDate.of(2016, 10, 8);
		assertEquals(DayOfWeek.SATURDAY, date.getDayOfWeek());
		assertEquals(DayOfWeek.MONDAY, date.with(weekEndAdjuster).getDayOfWeek());
	}
	
	@Test
	public void testMonthlyWithWeekendAdjustment() {
		LocalDate start = LocalDate.of(2016, 10, 3);
		LocalDate end = LocalDate.of(2017, 10, 3);
		
		List<LocalDate> dates = Stream.iterate(start, 
				d -> d.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(start, end))
				.collect(Collectors.toList());
		
		dates.stream()
			.forEach(d -> System.out.println(d.with(weekEndAdjuster)));
	}

	class NextWorkingDay implements TemporalAdjuster {

		@Override
		public Temporal adjustInto(Temporal temporal) {
			int daysToAdd = 0;
			DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
			if (dow == DayOfWeek.SUNDAY) {
				daysToAdd = 1;
			} else if (dow == DayOfWeek.SATURDAY) {
				daysToAdd = 2;
			}
			Temporal adjusted = temporal.plus(daysToAdd, ChronoUnit.DAYS);
			return adjusted;
		}
		
		
	}
}
