package com.alten.hotel_api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatesUtilTest {

    @Test
    public void getDatesBetween_ShouldReturnListOfDates(){
        // given
        final LocalDate today = LocalDate.now();
        final LocalDate twoDaysAfterToday = today.plusDays(2);
        LocalDate toBeAdded = today;

        final List<LocalDate> expectedListOfDates = new ArrayList<>();
        while (toBeAdded.isBefore(twoDaysAfterToday)){
            expectedListOfDates.add(toBeAdded);
            toBeAdded = toBeAdded.plus(1, ChronoUnit.DAYS);
        }

        // when
        List<LocalDate> listOfDatesBetween = DatesUtil.getDatesBetween(today, twoDaysAfterToday);

        // then
        assertThat(listOfDatesBetween).containsAll(expectedListOfDates);
    }


    @Test
    public void getDaysDiffBetweenDates_ShouldReturnDaysDiff(){
        // given
        final LocalDate today = LocalDate.now();
        final LocalDate twoDaysAfterToday = today.plusDays(2);
        final Integer expectedValue = 2;

        // when
        Integer returnedValue = DatesUtil.getDaysDiffBetween(today, twoDaysAfterToday);

        // then
        assertThat(returnedValue).isEqualTo(expectedValue);
    }
}
