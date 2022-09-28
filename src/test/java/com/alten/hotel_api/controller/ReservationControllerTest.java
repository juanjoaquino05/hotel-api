package com.alten.hotel_api.controller;

import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.model.Room;
import com.alten.hotel_api.model.User;
import com.alten.hotel_api.request.CreateReservationRequest;
import com.alten.hotel_api.response.CreateReservationResponse;
import com.alten.hotel_api.service.ReservationService;
import com.alten.hotel_api.service.RoomService;
import com.alten.hotel_api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.alten.hotel_api.constant.Reservations.DATE_FORMAT_PATTERN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {
    private ReservationController controller;

    @Mock
    private UserService userService;
    @Mock
    private ReservationService reservationService;

    @BeforeEach
    public void setUp(){
        controller = new ReservationController(reservationService);
    }

    @Test
    public void verifyCancelReservationForV1(){
        // given
        Long reservationId = 1L;
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        // when
        ResponseEntity response = controller.cancelReservation(userId, reservationId);

        // then
        assertThat(response).isInstanceOf(ResponseEntity.class);
    }

    @Test
    public void verifyModifyReservationForV1() throws Exception {
        // given
        Long reservationId = 1L;
        Long userId = 1L;
        Reservation reservation = createValidReservation();
        final ResponseEntity<CreateReservationResponse> expectedResponse = ResponseEntity.ok().body(new CreateReservationResponse());

        // when
        when(reservationService.modifyReservation(anyLong(), anyLong(), any(Reservation.class))).thenReturn(expectedResponse);
        ResponseEntity response = controller.modifyReservation(userId, reservationId, reservation);

        // then
        assertThat(response.getBody()).isInstanceOf(CreateReservationResponse.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void verifyPlaceReservationForV1() throws Exception {
        // given
        CreateReservationRequest request = createValidReservationRequest();
        final CreateReservationResponse expectedResponse = new CreateReservationResponse();

        // when
        when(reservationService.createRoomReservation(any(CreateReservationRequest.class))).thenReturn(expectedResponse);
        ResponseEntity response = controller.makeReservation(request);

        // then
        assertThat(response.getBody()).isInstanceOf(CreateReservationResponse.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    private CreateReservationRequest createValidReservationRequest(){

        CreateReservationRequest request = new CreateReservationRequest();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

        final LocalDate today = LocalDate.now();
        final LocalDate tomorrow = today.plusDays(1);
        final LocalDate threeDaysAfterToday = today.plusDays(3);

        request.setRoom(1L);
        request.setUser(1L);
        request.setStartDate(tomorrow.format(formatter));
        request.setEndDate(threeDaysAfterToday.format(formatter));

        return request;
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
}