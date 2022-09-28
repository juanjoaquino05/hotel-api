package com.alten.hotel_api.helper;

import com.alten.hotel_api.exception.InvalidDateException;
import com.alten.hotel_api.exception.InvalidDateFormatException;
import com.alten.hotel_api.request.CreateReservationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.alten.hotel_api.constant.Reservations.DATE_FORMAT_PATTERN;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReservationValidatorTest {
    @InjectMocks
    private ReservationValidator reservationValidator;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);



    @Test
    public void validateValidCreateRequest_ShouldntReturnExceptions() throws Exception {
        // given
        CreateReservationRequest request = createValidReservationRequest();

        // when
        reservationValidator.validateCreateRequest(request);

        // then
    }

    @Test
    public void validateCreateRequest_ShouldThrowExceptionWhenNull(){
        // given
        CreateReservationRequest request = null;

        // then
        assertThrows(Exception.class, () ->
        {
            // when
            reservationValidator.validateCreateRequest(request);
        });


    }

    @Test
    public void validateInvalidStartDateRequest_ShouldThrowExceptionWhenNull(){
        // given
        CreateReservationRequest request = createValidReservationRequest();
        request.setStartDate(LocalDate.parse(request.getStartDate(), formatter).minusDays(1).format(formatter));

        // then
        assertThrows(InvalidDateException.class, () ->
        {
            // when
            reservationValidator.validateCreateRequest(request);
        });


    }


    @Test
    public void validateInvalidStartDateFormatRequest_ShouldThrowException(){
        // given
        CreateReservationRequest request = createValidReservationRequest();
        request.setStartDate(LocalDate.parse(request.getStartDate(), formatter).atStartOfDay().toString());

        // then
        assertThrows(InvalidDateFormatException.class, () ->
        {
            // when
            reservationValidator.validateCreateRequest(request);
        });


    }

    @Test
    public void validateInvalidEndDateFormatRequest_ShouldThrowException(){
        // given
        CreateReservationRequest request = createValidReservationRequest();
        request.setEndDate(LocalDate.parse(request.getEndDate(), formatter).atStartOfDay().toString());

        // then
        assertThrows(InvalidDateFormatException.class, () ->
        {
            // when
            reservationValidator.validateCreateRequest(request);
        });


    }

    @Test
    public void validateStartDateAfterEndDateRequest_ShouldThrowException(){
        // given
        CreateReservationRequest request = createValidReservationRequest();
        request.setStartDate(LocalDate.parse(request.getStartDate(), formatter).plusDays(3).toString());

        // then
        assertThrows(InvalidDateException.class, () ->
        {
            // when
            reservationValidator.validateCreateRequest(request);
        });


    }

    @Test
    public void validateMoreThanAllowedDaysRequest_ShouldThrowException(){
        // given
        CreateReservationRequest request = createValidReservationRequest();
        request.setEndDate(LocalDate.parse(request.getEndDate(), formatter).plusDays(5).toString());

        // then
        assertThrows(InvalidDateException.class, () ->
        {
            // when
            reservationValidator.validateCreateRequest(request);
        });


    }


    private CreateReservationRequest createValidReservationRequest(){

        CreateReservationRequest request = new CreateReservationRequest();

        final LocalDate today = LocalDate.now();
        final LocalDate tomorrow = today.plusDays(1);
        final LocalDate threeDaysAfterToday = today.plusDays(3);

        request.setRoom(1L);
        request.setUser(1L);
        request.setStartDate(tomorrow.format(formatter));
        request.setEndDate(threeDaysAfterToday.format(formatter));

        return request;
    }
}
