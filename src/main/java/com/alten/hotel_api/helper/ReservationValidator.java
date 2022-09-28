package com.alten.hotel_api.helper;

import com.alten.hotel_api.constant.ErrorMessages;
import com.alten.hotel_api.exception.InvalidDateException;
import com.alten.hotel_api.exception.InvalidDateFormatException;
import com.alten.hotel_api.request.CreateReservationRequest;
import com.alten.hotel_api.util.DatesUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.alten.hotel_api.constant.Reservations.*;

@Component
public class ReservationValidator {
    public void validateCreateRequest(CreateReservationRequest request) throws Exception {
        // request cannot be null
        if(request == null) throw new Exception("null request");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

        // Valid Start Date
        if(request.getStartDate().isBlank() || request.getStartDate().isEmpty()) throw new Exception("");
        LocalDate startDate;
        try{
            startDate = LocalDate.parse(request.getStartDate(), formatter);
        } catch (Exception e){
            throw new InvalidDateFormatException("startDate");
        }

        // Can not reserve the same day
        LocalDate today = LocalDate.now();
        if(startDate.equals(today)) throw new InvalidDateException(ErrorMessages.TODAY_RESERVATION_ERROR);

        // Can not reserve a past date
        if(startDate.isBefore(today)) throw new InvalidDateException(ErrorMessages.PAST_DATE_RESERVATION);

        // Can not reserve a past date
        if(startDate.isAfter(today.plusDays(MAX_DAYS_IN_ADVANCE))) throw new InvalidDateException(ErrorMessages.MAX_DAYS_RESERVATION);

        // Valid End Date
        if(request.getEndDate().isBlank() || request.getEndDate().isEmpty()) throw new Exception("");
        LocalDate endDate;
        try{
            endDate = LocalDate.parse(request.getEndDate(), formatter);
        } catch (Exception e){
            throw new InvalidDateFormatException("endDate");
        }
        // Can not set an end date earlier than start date
        if(startDate.isAfter(endDate))
            throw new InvalidDateException(ErrorMessages.END_DATE_BEFORE_START_DATE);
        // Can not reserve more than 3 days
        if(DatesUtil.getDaysDiffBetween(startDate, endDate) >= MAX_RESERVATION_DAYS)
            throw new InvalidDateException(ErrorMessages.THREE_DAY_LIMIT);
    }
}
