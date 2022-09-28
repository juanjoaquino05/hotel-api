package com.alten.hotel_api.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class DatesUtil {
    public static List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    public static Integer getDaysDiffBetween(
            LocalDate startDate, LocalDate endDate) {

        long days = ChronoUnit.DAYS.between(startDate, endDate);

        return (int) days;
    }
}
