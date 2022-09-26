package com.example.hotel_api.service;

import com.example.hotel_api.model.Reservation;
import com.example.hotel_api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getRoomReservationsBetween(Long id, LocalDate start, LocalDate end){
        return reservationRepository.findAllByRoomIdAndStartDateBetween(id, start.atStartOfDay(), end.atStartOfDay());
    }
}
