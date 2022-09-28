package com.alten.hotel_api.service;

import com.alten.hotel_api.constant.Reservations;
import com.alten.hotel_api.model.Room;
import com.alten.hotel_api.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoomServiceTest {
    @InjectMocks
    private RoomService roomService;

    @Mock
    private ReservationService reservationService;

    @Mock
    private RoomRepository roomRepository;

    @Test
    public void checkAvailabilityForNewRoom_ShouldReturnListOfAvailableDates() {

        // given
        Room room = new Room();
        room.setId(1L);

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(Reservations.MIN_RESERVATION_START);
        LocalDate lastReservationPermitted = today.plusDays(Reservations.MAX_DAYS_IN_ADVANCE);

        LocalDate toBeAdded = tomorrow;
        final List<LocalDate> expectedListOfDates = new ArrayList<>();
        do{
            expectedListOfDates.add(toBeAdded);
            toBeAdded = toBeAdded.plus(1, ChronoUnit.DAYS);
        }
        while (toBeAdded.isBefore(lastReservationPermitted));

        // when
        List<LocalDate> returnedValue = roomService.checkAvailability(room);

        //then
        assertThat(returnedValue).isInstanceOf(List.class);
        verify(reservationService).getRoomReservationsBetween(room.getId(), tomorrow, lastReservationPermitted);
    }

    @Test
    public void getRoomWithExistingElement_ShouldReturnRoom() {

        // given
        Long existingRoomId = 1L;
        Room expectedRoom = new Room();
        expectedRoom.setId(existingRoomId);
        expectedRoom.setCreatedAt(LocalDateTime.now());
        expectedRoom.setNumber("C01");

        Optional<Room> opt = Optional.of(expectedRoom);

        // when
        when(roomRepository.findById(anyLong())).thenReturn(opt);
        Room returnedValue = roomService.getRoom(existingRoomId);

        //then
        assertThat(returnedValue).isInstanceOf(Room.class);
        assertThat(returnedValue.getId()).isEqualTo(existingRoomId);
    }
}
