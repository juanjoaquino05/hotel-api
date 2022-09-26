package com.alten.hotel_api.converter;

import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.model.Room;
import com.alten.hotel_api.model.User;
import com.alten.hotel_api.request.CreateReservationRequest;
import com.alten.hotel_api.response.CreateReservationResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.alten.hotel_api.constant.Reservations.DATE_FORMAT_PATTERN;

public class ReservationConverter {
    public static Reservation convertCreateRequest(CreateReservationRequest request, Room room, User user){
        Reservation reservation = new Reservation();

        reservation.setCreatedDate(LocalDateTime.now());
        reservation.setRoom(room);
        reservation.setUser(user);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        LocalDate start = LocalDate.parse(request.getStartDate(), formatter);
        LocalDate end = LocalDate.parse(request.getEndDate(), formatter);

        reservation.setStartDate(start.atStartOfDay());
        reservation.setEndDate(end.atTime(23, 59, 59));

        return reservation;
    }

    public static CreateReservationResponse convertReservationToResponse(Reservation reservation){
        CreateReservationResponse response = new CreateReservationResponse();

        response.setRoomNumber(reservation.getRoom().getNumber());
        response.setUser(reservation.getUser().getName());
        response.setStart(reservation.getStartDate());
        response.setEnd(reservation.getEndDate());
        response.setDate(reservation.getCreatedDate());

        return response;
    }
}
