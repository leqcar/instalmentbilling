package com.leqcar.sandbox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatesBetweenTest {

	public static void main(String[] args) {

		LocalDate start = LocalDate.of(2016, 10, 3);
		LocalDate end = LocalDate.of(2017, 10, 3);
		
		List<LocalDate> dates = Stream.iterate(start, 
				d -> {
					LocalDate result = d.plusMonths(1);
					while (result.getDayOfWeek() == DayOfWeek.SATURDAY || 
			        		result.getDayOfWeek() == DayOfWeek.SUNDAY) {
						result = result.plusDays(1);
					}						
					return result;
				})
				.limit(ChronoUnit.MONTHS.between(start, end))
				.collect(Collectors.toList());
		
		dates.forEach(System.out::println);
	}

}
