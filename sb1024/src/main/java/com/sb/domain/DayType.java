package com.sb.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public enum DayType {
    WEEKDAY, WEEKEND, HOLIDAY;

    public static DayType getDayType (LocalDate date) {

        if(date != null) {
            Set<LocalDate> holidays = getHolidaysForYear(date.getYear());
            if (holidays.contains(date)) {
                return HOLIDAY;
            }

            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (DayOfWeek.SATURDAY == dayOfWeek || DayOfWeek.SUNDAY == dayOfWeek) {
                return WEEKEND;
            }
            return WEEKDAY;
        }
        return null;
    }

    private static Set<LocalDate> getHolidaysForYear(int year) {
        Set<LocalDate> holidays = new HashSet<>();
        holidays.add(getIndependenceDay(year));
        holidays.add(getLaborDay(year));
        return holidays;
    }

    private static LocalDate getLaborDay(int year) {
        LocalDate laborDay = LocalDate.of(year, Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }
        return laborDay;
    }

    private static LocalDate getIndependenceDay(int year) {
        LocalDate independenceDay = LocalDate.of(year, Month.JULY, 4);
        if (DayOfWeek.SATURDAY == independenceDay.getDayOfWeek()) {
            independenceDay = independenceDay.minusDays(1);
        } else if (DayOfWeek.SUNDAY == independenceDay.getDayOfWeek()) {
            independenceDay = independenceDay.plusDays(1);
        }
        return independenceDay;
    }

}
