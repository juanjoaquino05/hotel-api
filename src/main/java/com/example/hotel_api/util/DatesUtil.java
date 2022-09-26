package com.example.hotel_api.util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DatesUtil {
    public static List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }
}
