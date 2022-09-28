package com.alten.hotel_api.repository;

import com.alten.hotel_api.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByRoomIdAndStartDateBetween(Long room, LocalDateTime startStartDate, LocalDateTime endStartDate);
    List<Reservation> findAllByRoomIdAndIsCancelledAndStartDateBetweenOrEndDateBetween(
            Long room, Boolean isCancelled, LocalDateTime startStartDate, LocalDateTime endStartDate, LocalDateTime startEndDate, LocalDateTime endEndDate);
}
