package com.sb.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Charge {
    private BigDecimal dailyCharge;
    private boolean weekDayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    public Charge(BigDecimal dailyCharge, boolean weekDayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.dailyCharge = dailyCharge;
        this.weekDayCharge = weekDayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public static boolean isChargeable(Charge charge, LocalDate currentDate) {
        DayType dayType = DayType.getDayType(currentDate);
        return  (dayType == DayType.HOLIDAY && charge.isHolidayCharge()) ||
                (dayType == DayType.WEEKEND && charge.isWeekendCharge()) ||
                (dayType == DayType.WEEKDAY && charge.isWeekDayCharge());
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekDayCharge() {
        return weekDayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
