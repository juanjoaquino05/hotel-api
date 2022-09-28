package com.alten.hotel_api.converter;

import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.model.Room;
import com.alten.hotel_api.model.User;
import com.alten.hotel_api.request.CreateReservationRequest;
import com.alten.hotel_api.response.CreateReservationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.alten.hotel_api.constant.Reservations.DATE_FORMAT_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReservationConverterTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    @Test
    public void convertValidReservation_ShouldReturnValidResponse() {
        // given
        Reservation reservation = createValidReservation();

        // when
        CreateReservationResponse returned = ReservationConverter.convertReservationToResponse(reservation);

        // then
        assertThat(returned).isInstanceOf(CreateReservationResponse.class);
    }

    @Test
    public void convertValidCreateReservationRequest_ShouldReturnValidReservation() {
        // given
        CreateReservationRequest request = createValidReservationRequest();
        Room room = new Room();
        room.setId(1L);

        User user = new User();
        user.setId(1L);

        // when
        Reservation returned = ReservationConverter.convertCreateRequest(request, room, user);

        // then
        assertThat(returned).isInstanceOf(Reservation.class);
    }

    @Test
    public void updateReservationData_ShouldUpdateCorrectly() {
        // given
        Reservation reservationToBeUpdated = createValidReservation();

        Reservation reservationData = createValidReservation();
        reservationData.setStartDate(reservationData.getStartDate().plusDays(2));
        reservationData.setEndDate(reservationData.getEndDate().plusDays(2));

        // when
        ReservationConverter.updateReservationData(reservationToBeUpdated, reservationData);

        // then
        assertThat(reservationToBeUpdated.getStartDate()).isEqualTo(reservationData.getStartDate());
        assertThat(reservationToBeUpdated.getEndDate()).isEqualTo(reservationData.getEndDate());
    }
    @Test
    public void convertValidReservationToCreateRequest_ShouldReturnValidRequest() {
        // given
        Reservation reservation = createValidReservation();

        // when
        CreateReservationRequest returned = ReservationConverter.convertReservationToCreateRequest(reservation);

        // then
        assertThat(returned).isInstanceOf(CreateReservationRequest.class);
    }

    private Reservation createValidReservation() {

        Room room = new Room();
        room.setId(1L);

        User user = new User();
        user.setId(1L);

        final LocalDate today = LocalDate.now();
        final LocalDate tomorrow = today.plusDays(1);
        final LocalDate threeDaysAfterToday = today.plusDays(3);

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setStartDate(tomorrow.atStartOfDay());
        reservation.setEndDate(threeDaysAfterToday.atTime(23, 59, 59));

        return reservation;
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
