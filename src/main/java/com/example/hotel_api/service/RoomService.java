package com.example.hotel_api.service;

import com.example.hotel_api.exception.ResourceNotFoundException;
import com.example.hotel_api.model.Reservation;
import com.example.hotel_api.model.Room;
import com.example.hotel_api.repository.RoomRepository;
import com.example.hotel_api.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.hotel_api.constant.Reservations.MAX_DAYS_IN_ADVANCE;
import static com.example.hotel_api.constant.Reservations.MIN_RESERVATION_START;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private ReservationService reservationService;

    @Autowired
    public RoomService(RoomRepository roomRepository, ReservationService reservationService) {
        this.roomRepository = roomRepository;
        this.reservationService = reservationService;
    }

    public List<LocalDate> checkAvailability(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate start = today.plusDays(MIN_RESERVATION_START);
        LocalDate end = today.plusDays(MAX_DAYS_IN_ADVANCE);

        List<LocalDate> availability = DatesUtil.getDatesBetween(start, end);

        removeReservedDays(room, availability, start, end);

        return availability;
    }

    private void removeReservedDays(Room room, List<LocalDate> availability, LocalDate start, LocalDate end) {
        Long roomId = room.getId();
        List<Reservation> reservations = reservationService.getRoomReservationsBetween(roomId, start, end);
        Set<LocalDate> datesToRemove = new HashSet<>();

        reservations.forEach(reservation -> {
            List<LocalDate> dates = DatesUtil.getDatesBetween(reservation.getStartDate().toLocalDate(), reservation.getEndDate().toLocalDate());
            datesToRemove.addAll(dates);
        });

        datesToRemove.forEach(date -> {
            availability.remove(date);
        });
    }

    public Room getRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(!room.isPresent()) throw new ResourceNotFoundException("Room isn't available for the moment");

        return room.get();
    }
}
