package com.alten.hotel_api.controller;

import com.alten.hotel_api.model.Room;
import com.alten.hotel_api.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTest {
    private RoomController controller;

    @Mock
    private RoomService roomService;

    @BeforeEach
    public void setUp(){
        controller = new RoomController(roomService);
    }

    @Test
    public void verifyRoomAvailabilityForV1(){
        // given
        Room room = new Room();
        room.setId(1L);
        room.setNumber("1A");

        // when
        when(roomService.getRoom(room.getId())).thenReturn(room);
        ResponseEntity<List<LocalDate>> response = controller.getAvailability(room.getId());

        // then
        assertThat(response).isInstanceOf(ResponseEntity.class);
        assertThat(response.getBody()).isInstanceOf(List.class);
    }
}
